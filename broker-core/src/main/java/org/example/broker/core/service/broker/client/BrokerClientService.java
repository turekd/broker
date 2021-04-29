package org.example.broker.core.service.broker.client;

import org.example.broker.api.domain.Client;
import org.example.broker.core.service.broker.publisher.MessagePublisher;

public interface BrokerClientService {
    void register(MessagePublisher messagePublisher, Client client);
}
