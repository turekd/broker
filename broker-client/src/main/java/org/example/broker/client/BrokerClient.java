package org.example.broker.client;

import org.example.broker.api.domain.Client;
import org.example.broker.api.domain.Message;

public interface BrokerClient {
    void registerClient(Client client);

    void publishMessageOnTopic(Message message, String topic);
}
