package io.fourfinanceit.service;

import com.google.common.base.Preconditions;
import io.fourfinanceit.exceptions.RiskException;
import io.fourfinanceit.model.Client;
import io.fourfinanceit.model.enums.ClientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RiskServiceImpl implements RiskService {

    @Autowired
    private ClientService clientService;

    @Override
    public void analyze(Long clientId) {
        Preconditions.checkNotNull(clientId, "'clientId' is undefined");

        Client client = clientService.getClientById(clientId);
        ClientStatus status = ClientStatus.getByValue(client.getStatus());

        if (ClientStatus.DIRTY == status) {
            throw new RiskException("Loan is rejected. Decided by risk analysis.");
        }
    }

}
