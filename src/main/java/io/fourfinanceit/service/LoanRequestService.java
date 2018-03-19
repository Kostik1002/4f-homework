package io.fourfinanceit.service;

import java.math.BigDecimal;

public interface LoanRequestService {

    void requestLoan(Long clientId, BigDecimal amount, int termInDays, String clientRemoteAddress);

}
