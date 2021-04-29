package org.example.broker.api.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String content;
    private LocalDateTime expiresAt;
}
