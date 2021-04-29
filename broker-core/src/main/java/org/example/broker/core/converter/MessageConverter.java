package org.example.broker.core.converter;

import org.example.broker.api.domain.Message;
import org.example.broker.core.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public MessageEntity toEntity(Message message, String topic) {
        var entity = new MessageEntity();
        entity.setTopic(topic);
        entity.setContent(message.getContent());
        entity.setExpiresAt(message.getExpiresAt());
        return entity;
    }

}
