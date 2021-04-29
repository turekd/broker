package org.example.broker.core.converter;

import org.example.broker.api.domain.Client;
import org.example.broker.core.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public ClientEntity toEntity(Client client) {
        var entity = new ClientEntity();
        entity.setTopic(client.getTopic());
        entity.setUrl(client.getUrl());
        return entity;
    }

    public Client fromEntity(ClientEntity entity) {
        var client = new Client();
        client.setTopic(entity.getTopic());
        client.setUrl(entity.getUrl());
        return client;
    }

}
