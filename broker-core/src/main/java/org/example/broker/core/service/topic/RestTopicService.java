package org.example.broker.core.service.topic;

import lombok.RequiredArgsConstructor;
import org.example.broker.api.domain.Message;
import org.example.broker.core.service.broker.message.BrokerMessageService;
import org.example.broker.core.service.broker.publisher.MessagePublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestTopicService implements TopicService {

    private final MessagePublisher restMessagePublisher;
    private final BrokerMessageService brokerMessageService;

    @Override
    public void publishMessageOnTopic(String topic, Message message) {
        brokerMessageService.publish(restMessagePublisher, topic, message);
    }

}
