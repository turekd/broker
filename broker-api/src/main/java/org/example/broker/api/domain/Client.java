package org.example.broker.api.domain;

import lombok.Data;

@Data
public class Client {
    private String topic;
    private String url;
}
