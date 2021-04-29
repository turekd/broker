package org.example.broker.core.service.broker.publisher;

import org.example.broker.api.domain.Client;

import java.util.List;
import java.util.Set;

public interface MessagePublisher {

    void publishMessage(Set<Client> clients, String message);

    void publishMessagesToClient(List<String> messages, Client client);

}
