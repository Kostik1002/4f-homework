package io.fourfinanceit.service;

import io.fourfinanceit.model.Client;

public interface ClientService {

    Client getClientById(Long clientId);

    void checkClient(Long clientId);
}
