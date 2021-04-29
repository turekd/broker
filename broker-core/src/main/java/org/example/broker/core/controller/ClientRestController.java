package org.example.broker.core.controller;

import lombok.RequiredArgsConstructor;
import org.example.broker.api.domain.Client;
import org.example.broker.core.service.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void register(@RequestBody Client client) {
        clientService.register(client);
    }

}
