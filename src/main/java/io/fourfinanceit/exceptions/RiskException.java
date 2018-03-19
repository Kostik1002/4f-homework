package io.fourfinanceit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RiskException extends RuntimeException {

    public RiskException(String message) {
        super(message);
    }

}
