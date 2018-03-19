package io.fourfinanceit.service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.fourfinanceit.BaseTest;
import io.fourfinanceit.exceptions.LoanException;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanDetail;
import io.fourfinanceit.repository.LoanDetailsRepository;
import io.fourfinanceit.repository.LoanRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Date;

public class LoanServiceTest  extends BaseTest {

    private static final Long CLIENT_ID = 9L;
    private static final Long NON_EXISTING_LOAN_ID = 999L;
    private static final int INITIAL_TERM = 13;
    private static final int EXTENDED_TERM = 17;

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanDetailsRepository loanDetailsRepository;

    @Test
    @DirtiesContext
    public void loanCanBeCreated() {
        Loan loan = loanService.persistLoan(CLIENT_ID, BigDecimal.TEN, INITIAL_TERM);
        Assert.assertNotNull(loanRepository.findById(loan.getId()));
    }

    @Test
    @DirtiesContext
    public void loanCanBeExtended() {
        Loan loan = loanService.persistLoan(CLIENT_ID, BigDecimal.TEN, INITIAL_TERM);
        Iterable<LoanDetail> detailsBeforeExtension = loanDetailsRepository.findAllById(Lists.newArrayList(loan.getId()));
        Assert.assertEquals(1, Iterables.size(detailsBeforeExtension));

        loanService.extendLoan(CLIENT_ID, loan.getId(), EXTENDED_TERM);
        Iterable<LoanDetail> detailsAfterExtension = loanDetailsRepository.findAllByLoanId(loan.getId());
        Assert.assertEquals(2, Iterables.size(detailsAfterExtension));
    }

    @Test(expected = LoanException.class)
    @DirtiesContext
    public void throwExceptionIfLoanDoesNotExist() {
        loanService.extendLoan(CLIENT_ID, NON_EXISTING_LOAN_ID, EXTENDED_TERM);
    }

    @Test
    @DirtiesContext
    public void newTermShouldBeGreaterThanPrevious() {
        Loan loan = loanService.persistLoan(CLIENT_ID, BigDecimal.TEN, INITIAL_TERM);
        Date initialTerm = loanDetailsRepository.findLastExtensionDateByLoanId(loan.getId());
        loanService.extendLoan(CLIENT_ID, loan.getId(), EXTENDED_TERM);
        Date extendedDate = loanDetailsRepository.findLastExtensionDateByLoanId(loan.getId());

        Assert.assertTrue(extendedDate.compareTo(initialTerm) > 0);
    }

}
