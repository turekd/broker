package org.example.broker.core.service.broker.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.broker.api.domain.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestMessagePublisher implements MessagePublisher {

    private final RestTemplate restTemplate;

    @Override
    public void publishMessage(Set<Client> clients, String message) {
        clients.forEach(client -> CompletableFuture.runAsync(() -> makeRequest(client.getUrl(), message)));
    }

    @Override
    public void publishMessagesToClient(List<String> messages, Client client) {
        messages.forEach(message -> makeRequest(client.getUrl(), message));
    }

    private void makeRequest(String url, String message) {
        log.debug("Sending message to {}", url);
        try {
            restTemplate.postForObject(url, message, String.class);
        } catch (Exception e) {
            log.error("Failed to publish message to: " + url, e);
        }
    }
}
