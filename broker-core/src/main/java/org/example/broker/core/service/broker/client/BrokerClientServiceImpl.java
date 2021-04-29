package org.example.broker.core.service.broker.client;

import lombok.RequiredArgsConstructor;
import org.example.broker.api.domain.Client;
import org.example.broker.core.converter.ClientConverter;
import org.example.broker.core.entity.MessageEntity;
import org.example.broker.core.repository.ClientRepository;
import org.example.broker.core.repository.MessageRepository;
import org.example.broker.core.service.broker.publisher.MessagePublisher;
import org.example.broker.core.validator.ClientValidator;
import org.example.broker.core.validator.ValidationException;
import org.example.broker.core.validator.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrokerClientServiceImpl implements BrokerClientService {

    private final ClientValidator clientValidator;
    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;
    private final ClientConverter clientConverter;

    @Override
    public void register(MessagePublisher messagePublisher, Client client) {
        final ValidationResult validationResult = this.clientValidator.validate(client);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        clientRepository.save(clientConverter.toEntity(client));

        CompletableFuture.runAsync(() -> publishMessagesToNewClient(messagePublisher, client));
    }

    private void publishMessagesToNewClient(MessagePublisher messagePublisher, Client client) {
        final List<String> messages = messageRepository.findAllByTopic(client.getTopic()).stream()
            .map(MessageEntity::getContent)
            .collect(Collectors.toUnmodifiableList());
        messagePublisher.publishMessagesToClient(messages, client);
    }

}
