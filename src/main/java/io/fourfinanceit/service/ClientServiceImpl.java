package io.fourfinanceit.service;

import com.google.common.base.Preconditions;
import io.fourfinanceit.exceptions.ClientNotFoundException;
import io.fourfinanceit.model.Client;
import io.fourfinanceit.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Service
class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getClientById(Long clientId) {
        Preconditions.checkNotNull(clientId, "'clientId' is undefined");
        return clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId) );
    }

    @Override
    public void checkClient(Long clientId) {
        Preconditions.checkNotNull(clientId, "'clientId' is undefined");
        getClientById(clientId);
    }
}
