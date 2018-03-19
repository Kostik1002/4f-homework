package io.fourfinanceit.service;

import com.google.common.annotations.VisibleForTesting;
import io.fourfinanceit.model.Client;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanDetail;
import io.fourfinanceit.model.enums.LoanExtensionStatus;
import io.fourfinanceit.model.to.ClientTO;
import io.fourfinanceit.model.to.LoanDetailTO;
import io.fourfinanceit.model.to.LoanTO;
import io.fourfinanceit.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
class ClientInfoServiceImpl implements ClientInfoService {

    private static final int DAYS_IN_WEEK = 7;

    @Autowired
    private ClientService clientService;

    @Value("${loan.factor-per-week}")
    private BigDecimal loanFactorPerWeek;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClientTO getClientInfo(Long id) {
        checkNotNull(id, "'id' is undefined");

        Client client = clientService.getClientById(id);

        ClientTO clientTO = new ClientTO();
        clientTO.setName(client.getName());
        clientTO.setSurname(client.getSurname());
        clientTO.setPhone(client.getPhone());
        clientTO.setLoans(convertLoansToLoansTO(client.getLoans()));

        return clientTO;
    }

    private List<LoanTO> convertLoansToLoansTO(List<Loan> loans) {
        return loans.stream()
                .map(this::loanToLoanTO)
                .collect(Collectors.toList());
    }

    private LoanTO loanToLoanTO(Loan loan) {
        LoanTO loanTO = new LoanTO();
        loanTO.setAmount(loan.getAmount());
        loanTO.setLoanDate(loan.getDate());
        loanTO.setStatus(loan.getStatus());
        loanTO.setDetails(convertLoanDetailsToLoanDetailsTO(loan.getLoanDetails()));
        loanTO.setTotal(calculateLoanFactor(loan.getLoanDetails(), loan.getAmount()));
        return loanTO;
    }

    private List<LoanDetailTO> convertLoanDetailsToLoanDetailsTO(List<LoanDetail> loanDetails) {
        return loanDetails.stream()
                .map(this::loanDetailToLoanDetailTO)
                .collect(Collectors.toList());
    }

    private LoanDetailTO loanDetailToLoanDetailTO(LoanDetail loanDetail) {
        LoanDetailTO detail = new LoanDetailTO();
        detail.setExtensionDate(loanDetail.getDate());
        detail.setTerm(loanDetail.getTerm());
        return detail;
    }

    private BigDecimal calculateLoanFactor(List<LoanDetail> loanDetails, BigDecimal amount) {
        LocalDate initialDate = DateUtils.dateToLocalDate(getLoanInitialDate(loanDetails));
        LocalDate lastTermDate = DateUtils.dateToLocalDate(getLoanLastTerm(loanDetails));

        Period period = Period.between(initialDate, lastTermDate);
        int weeks = calculateWeeks(period.getDays());
        return calculateTotalSum(amount, weeks).setScale(2, RoundingMode.HALF_UP);
    }

    private Date getLoanInitialDate(List<LoanDetail> loanDetails) {
        return loanDetails.stream()
                .filter(detail -> LoanExtensionStatus.INITIAL == LoanExtensionStatus.getByValue(detail.getStatus()))
                .map(LoanDetail::getDate)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Date getLoanLastTerm(List<LoanDetail> loanDetails) {
        return loanDetails.stream()
                .map(LoanDetail::getTerm)
                .max(Date::compareTo)
                .orElseThrow(IllegalStateException::new);
    }

    @VisibleForTesting
    protected int calculateWeeks(int days) {
        int weeks = days / DAYS_IN_WEEK;
        return days % DAYS_IN_WEEK == 0 ? weeks : weeks + 1;
    }

    @VisibleForTesting
    protected BigDecimal calculateTotalSum(BigDecimal amount, int weeks) {
        BigDecimal weeksTotalLoanFactor = loanFactorPerWeek.multiply(BigDecimal.valueOf(weeks));
        BigDecimal loanFactorSum = amount.multiply(weeksTotalLoanFactor).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        return amount.add(loanFactorSum);
    }
}
