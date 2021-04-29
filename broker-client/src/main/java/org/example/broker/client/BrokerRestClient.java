package org.example.broker.client;

import org.example.broker.api.domain.Client;
import org.example.broker.api.domain.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class BrokerRestClient implements BrokerClient {

    private final String brokerServerUrl;
    private final RestOperations restOperations;

    public BrokerRestClient(String brokerServerUrl, RestOperations restOperations) {
        this.brokerServerUrl = brokerServerUrl;
        this.restOperations = restOperations;
    }

    @Override
    public void registerClient(Client client) {
        final HttpEntity<Client> requestEntity = new HttpEntity<>(client);
        restOperations.exchange(brokerServerUrl + "/client", HttpMethod.PUT, requestEntity, Client.class);
    }

    @Override
    public void publishMessageOnTopic(Message message, String topic) {
        final HttpEntity<Message> requestEntity = new HttpEntity<>(message);
        restOperations.exchange(
            brokerServerUrl + "/topic/" + topic + "/message",
            HttpMethod.PUT,
            requestEntity,
            Message.class
        );
    }
}
