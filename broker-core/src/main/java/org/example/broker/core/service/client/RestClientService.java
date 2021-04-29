package org.example.broker.core.service.client;

import lombok.RequiredArgsConstructor;
import org.example.broker.api.domain.Client;
import org.example.broker.core.service.broker.client.BrokerClientService;
import org.example.broker.core.service.broker.publisher.MessagePublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestClientService implements ClientService {

    private final MessagePublisher restMessagePublisher;
    private final BrokerClientService brokerClientService;

    @Override
    public void register(Client client) {
        brokerClientService.register(restMessagePublisher, client);
    }

}
