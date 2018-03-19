package io.fourfinanceit.service;

import io.fourfinanceit.model.to.ClientTO;

public interface ClientInfoService {

    ClientTO getClientInfo(Long id);

}
