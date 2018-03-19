package io.fourfinanceit.service;

import com.google.common.base.Preconditions;
import io.fourfinanceit.exceptions.LoanException;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanDetail;
import io.fourfinanceit.model.enums.LoanExtensionStatus;
import io.fourfinanceit.model.enums.LoanStatus;
import io.fourfinanceit.repository.LoanDetailsRepository;
import io.fourfinanceit.repository.LoanRepository;
import io.fourfinanceit.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static java.util.Objects.isNull;

@Service
class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanDetailsRepository loanDetailsRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Loan persistLoan(Long clientId, BigDecimal amount, int termInDays) {
        Preconditions.checkNotNull(clientId, "'clientId' is undefined");
        Preconditions.checkNotNull(amount, "'amount' is undefined");

        return createAndSaveLoan(clientId, amount, termInDays);
    }

    private Loan createAndSaveLoan(Long clientId, BigDecimal amount, int termInDays) {
        Loan loan = createLoan(clientId, amount);
        loanRepository.save(loan);
        loanDetailsRepository.save(createLoanDetail(loan.getId(), loan.getDate(), termInDays, LoanExtensionStatus.INITIAL));
        return loan;
    }

    private Loan createLoan(Long clientId, BigDecimal amount) {
        Loan loan = new Loan();
        loan.setAmount(amount);
        loan.setClientId(clientId);
        loan.setStatus(LoanStatus.OPENED.getValue());
        loan.setDate(new Date());

        return loan;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoanDetail extendLoan(Long clientId, Long loanId, int termInDays) {
        Preconditions.checkNotNull(clientId, "'clientId' is undefined");
        Preconditions.checkNotNull(loanId, "'loanId' is undefined");

        checkLoanOnExistence(clientId, loanId);

        Date newTerm = calculateNewTerm(loanId, termInDays);
        LoanDetail loanDetail = createLoanDetail(loanId, newTerm, termInDays, LoanExtensionStatus.EXTENDED);
        return loanDetailsRepository.save(loanDetail);
    }

    private void checkLoanOnExistence(Long clientId, Long loanId) {
        Loan loan = loanRepository.findByIdAndClientId(loanId, clientId);
        if (isNull(loan)) {
            throw new LoanException("Requested loan does not exist");
        }
    }

    private Date calculateNewTerm(Long loanId, int daysCount) {
        Date lastExtensionDate = loanDetailsRepository.findLastExtensionDateByLoanId(loanId);
        return DateUtils.addDays(lastExtensionDate, daysCount);
    }

    private LoanDetail createLoanDetail(Long loanId, Date loanDate, int termInDays, LoanExtensionStatus status) {
        LoanDetail detail = new LoanDetail();
        detail.setLoanId(loanId);
        detail.setDate(new Date());
        detail.setTerm(DateUtils.addDays(loanDate, termInDays));
        detail.setStatus(status.getValue());

        return detail;
    }

}
