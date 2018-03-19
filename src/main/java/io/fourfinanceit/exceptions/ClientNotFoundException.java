package io.fourfinanceit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long clientId) {
        super(String.format("Client with ID '%s' does not exist", clientId));
    }

}
