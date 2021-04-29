package org.example.broker.core.validator;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.example.broker.api.domain.Client;
import org.example.broker.core.repository.ClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private static final String TOPIC_REGEX = "^[a-z0-9\\-]+$";
    private final UrlValidator urlValidator;
    private final ClientRepository clientRepository;

    public ValidationResult validate(Client client) {
        final List<String> errors = new ArrayList<>();

        if (StringUtils.isEmpty(client.getTopic())) {
            errors.add("Topic can not be empty");
        }
        if (!StringUtils.isEmpty(client.getTopic()) && isTopicInvalid(client.getTopic())) {
            errors.add("Topic must match " + TOPIC_REGEX);
        }
        if (!urlValidator.isValid(client.getUrl())) {
            errors.add("Provided url is not a valid URL");
        }
        if (clientRepository.findByTopicAndUrl(client.getTopic(), client.getUrl()).isPresent()) {
            errors.add("Client already exists");
        }

        return ValidationResult.builder()
            .isValid(errors.isEmpty())
            .errors(errors)
            .build();
    }

    private boolean isTopicInvalid(String topic) {
        return !topic.matches(TOPIC_REGEX);
    }

}
