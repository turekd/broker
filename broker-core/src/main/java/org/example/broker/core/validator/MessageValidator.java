package org.example.broker.core.validator;

import org.example.broker.api.domain.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageValidator {

    public ValidationResult validate(Message message) {
        final List<String> errors = new ArrayList<>();

        if (StringUtils.isEmpty(message.getContent())) {
            errors.add("Content can not be empty");
        }

        if (StringUtils.isEmpty(message.getExpiresAt())) {
            errors.add("Expiration datetime can not be empty");
        }
        if (!StringUtils.isEmpty(message.getExpiresAt()) && LocalDateTime.now().isAfter(message.getExpiresAt())) {
            errors.add("Expiration datetime can not be a past date");
        }

        return ValidationResult.builder()
            .isValid(errors.isEmpty())
            .errors(errors)
            .build();
    }

}
