package io.fourfinanceit.service;

import io.fourfinanceit.BaseTest;
import io.fourfinanceit.exceptions.ClientNotFoundException;
import io.fourfinanceit.exceptions.LoanException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

public class LoanRequestServiceTest  extends BaseTest {

    private static final Long CLIENT_ID = 1L;
    private static final Long NON_EXISTING_CLIENT_ID = 999L;

    @Value("${loan.max-possible-amount}")
    private BigDecimal maxPossibleAmount;

    @Value("${loan.max-term-days}")
    private int maxTermDays;

    @Autowired
    private LoanRequestService loanRequestService;

    @Test(expected = ClientNotFoundException.class)
    @DirtiesContext
    public void requestLoanForNonExistingClient() {
        loanRequestService.requestLoan(NON_EXISTING_CLIENT_ID, BigDecimal.valueOf(-1), 12, "localhost");
    }

    @Test(expected = LoanException.class)
    @DirtiesContext
    public void requestLoanForNegativeAmount() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.valueOf(-1), 12, "localhost");
    }

    @Test(expected = LoanException.class)
    @DirtiesContext
    public void validateAmountForZeroValue() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.ZERO, 12, "localhost");
    }

    @Test
    @DirtiesContext
    public void validateAmountForPositiveValue() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.ONE, 12, "localhost");
    }

    @Test
    @DirtiesContext
    public void validateAmountForMaxValue() {
        loanRequestService.requestLoan(CLIENT_ID, maxPossibleAmount, 12, "localhost");
    }

    @Test(expected = LoanException.class)
    @DirtiesContext
    public void validateAmountWhereValueIsGreaterThanMaxAvailableValue() {
        loanRequestService.requestLoan(CLIENT_ID, maxPossibleAmount.add(BigDecimal.ONE), 12, "localhost");
    }

    @Test(expected = LoanException.class)
    @DirtiesContext
    public void validateTermForNegativeValue() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.ONE, -1, "localhost");
    }

    @Test(expected = LoanException.class)
    @DirtiesContext
    public void validateTermForZeroValue() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.ONE, 0, "localhost");
    }

    @Test
    @DirtiesContext
    public void validateTermForPositiveValue() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.ONE, 1, "localhost");
    }

    @Test
    @DirtiesContext
    public void validateTermForMaxValue() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.ONE, maxTermDays, "localhost");
    }

    @Test(expected = LoanException.class)
    @DirtiesContext
    public void validateTermWhereValueIsGreaterThanMaxAvailableValue() {
        loanRequestService.requestLoan(CLIENT_ID, BigDecimal.ONE, maxTermDays + 1, "localhost");
    }


}
