package org.example.broker.core.service.broker.message;

import lombok.RequiredArgsConstructor;
import org.example.broker.api.domain.Client;
import org.example.broker.api.domain.Message;
import org.example.broker.core.converter.ClientConverter;
import org.example.broker.core.converter.MessageConverter;
import org.example.broker.core.repository.ClientRepository;
import org.example.broker.core.repository.MessageRepository;
import org.example.broker.core.service.broker.publisher.MessagePublisher;
import org.example.broker.core.validator.MessageValidator;
import org.example.broker.core.validator.ValidationException;
import org.example.broker.core.validator.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrokerMessageServiceImpl implements BrokerMessageService {

    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;
    private final MessageValidator messageValidator;
    private final ClientConverter clientConverter;
    private final MessageConverter messageConverter;

    @Override
    public void publish(MessagePublisher messagePublisher, String topic, Message message) {
        final ValidationResult validationResult = this.messageValidator.validate(message);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        messageRepository.save(messageConverter.toEntity(message, topic));

        final Set<Client> clients = clientRepository.findAllByTopic(topic).stream()
            .map(clientConverter::fromEntity)
            .collect(Collectors.toUnmodifiableSet());
        messagePublisher.publishMessage(clients, message.getContent());
    }

}
