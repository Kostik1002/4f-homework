package io.fourfinanceit.service;

import io.fourfinanceit.BaseTest;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.to.ClientTO;
import io.fourfinanceit.model.to.LoanTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ClientInfoServiceTest extends BaseTest {

    private static final Long CLIENT_ID = 9L;
    private static final BigDecimal AMOUNT = BigDecimal.valueOf(100);

    @Autowired
    private ClientInfoService clientInfoService;

    @Autowired
    private LoanService loanService;

    @Test
    @DirtiesContext
    public void totalAmountForFullWeek() {
        loanService.persistLoan(CLIENT_ID, AMOUNT, 14);
        ClientTO clientInfo = clientInfoService.getClientInfo(CLIENT_ID);
        LoanTO loanTO = clientInfo.getLoans().get(0);
        assertEquals(toBigDecimal(103), loanTO.getTotal());
    }

    @Test
    @DirtiesContext
    public void totalAmountForIncompleteWeek() {
        loanService.persistLoan(CLIENT_ID, AMOUNT, 15);
        ClientTO clientInfo = clientInfoService.getClientInfo(CLIENT_ID);
        LoanTO loanTO = clientInfo.getLoans().get(0);
        assertEquals(toBigDecimal(104.50), loanTO.getTotal());
    }

    @Test
    @DirtiesContext
    public void totalAmountForExtendedLoan() {
        Loan loan = loanService.persistLoan(CLIENT_ID, AMOUNT, 15);
        loanService.extendLoan(CLIENT_ID, loan.getId(), 30);
        ClientTO clientInfo = clientInfoService.getClientInfo(CLIENT_ID);
        LoanTO loanTO = clientInfo.getLoans().get(0);
        assertEquals(toBigDecimal(107.50), loanTO.getTotal());
    }

    private BigDecimal toBigDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
