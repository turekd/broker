package org.example.client.test.publisher;

import org.example.broker.client.BrokerClient;
import org.example.broker.client.BrokerRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PublisherApplication {

    public static final List<String> TOPICS = List.of(
        "dummy1",
        "dummy2",
        "dummy3"
    );

    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }

    @Bean
    BrokerClient brokerClient(RestTemplateBuilder restTemplateBuilder, @Value("${broker.server.url}") String url) {
        return new BrokerRestClient(url, restTemplateBuilder.build());
    }

}
