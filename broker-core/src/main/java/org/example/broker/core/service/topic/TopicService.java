package org.example.broker.core.service.topic;

import org.example.broker.api.domain.Message;

public interface TopicService {

    void publishMessageOnTopic(String topic, Message message);

}
