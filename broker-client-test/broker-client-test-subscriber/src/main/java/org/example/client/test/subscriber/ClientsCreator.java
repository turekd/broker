package org.example.client.test.subscriber;

import org.example.broker.api.domain.Client;
import org.example.broker.client.BrokerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientsCreator implements ApplicationRunner {

    private final BrokerClient brokerClient;
    private final String serverPort;

    public ClientsCreator(BrokerClient brokerClient, @Value("${server.port}") String serverPort) {
        this.brokerClient = brokerClient;
        this.serverPort = serverPort;
    }

    @Override
    public void run(ApplicationArguments args) {
        var url = "http://127.0.0.1:" + serverPort;
        SubscriberApplication.TOPICS.forEach(topic -> {
            Client client = new Client();
            client.setTopic(topic);
            client.setUrl(url + "/subscriber/" + topic);
            System.out.println("Registering client: " + client);
            try {
                brokerClient.registerClient(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
