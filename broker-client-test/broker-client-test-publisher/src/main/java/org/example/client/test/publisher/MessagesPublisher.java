package org.example.client.test.publisher;

import lombok.RequiredArgsConstructor;
import org.example.broker.api.domain.Message;
import org.example.broker.client.BrokerClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class MessagesPublisher implements ApplicationRunner {

    private final BrokerClient brokerClient;

    @Override
    public void run(ApplicationArguments args) {
        PublisherApplication.TOPICS.forEach(topic -> {
            for (int i = 0; i < 3; i++) {
                Message message = new Message();
                message.setContent("should be delivered #" + i);
                message.setExpiresAt(LocalDateTime.now().plusMinutes(10));
                CompletableFuture.runAsync(() -> brokerClient.publishMessageOnTopic(message, topic));
            }
            for (int i = 0; i < 3; i++) {
                Message message = new Message();
                message.setContent("should NOT be delivered #" + i);
                message.setExpiresAt(LocalDateTime.now().plusSeconds(5));
                CompletableFuture.runAsync(() -> brokerClient.publishMessageOnTopic(message, topic));
            }
        });
    }
}
