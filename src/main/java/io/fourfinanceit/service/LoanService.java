package io.fourfinanceit.service;

import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanDetail;

import java.math.BigDecimal;

public interface LoanService {

    Loan persistLoan(Long clientId, BigDecimal amount, int termInDays);

    LoanDetail extendLoan(Long clientId, Long loanId, int termInDays);
}
