package org.example.broker.core.service.broker.message;

import org.example.broker.api.domain.Message;
import org.example.broker.core.service.broker.publisher.MessagePublisher;

public interface BrokerMessageService {
    void publish(MessagePublisher messagePublisher, String topic, Message message);
}
