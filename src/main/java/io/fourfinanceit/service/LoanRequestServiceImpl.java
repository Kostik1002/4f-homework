package io.fourfinanceit.service;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import io.fourfinanceit.exceptions.LoanException;
import io.fourfinanceit.model.LoanRequest;
import io.fourfinanceit.repository.LoanRequestRepository;
import io.fourfinanceit.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

@Service
class LoanRequestServiceImpl implements LoanRequestService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RiskService riskService;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanService loanService;

    @Value("${loan.max-possible-amount}")
    private BigDecimal maxPossibleAmount;

    @Value("${loan.max-term-days}")
    private int maxTermDays;

    @Value("${loan.risk.time.from}")
    private String riskTimeFrom;

    @Value("${loan.risk.time.to}")
    private String riskTimeTo;

    @Value("${loan.max-applications-per-day}")
    private long maxApplicationsPerDay;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void requestLoan(Long clientId, BigDecimal amount, int termInDays, String clientRemoteAddress) {
        checkNotNull(clientId, "'clientId' is undefined");
        checkNotNull(amount, "'amount' is undefined");
        checkNotNull(clientRemoteAddress, "'clientRemoteAddress' is undefined");

        validateRequest(clientId, amount, termInDays);
        analyzeRiskIfRequired(clientId, amount, clientRemoteAddress);
        createAndSaveLoanRequest(clientId, clientRemoteAddress);
        loanService.persistLoan(clientId, amount, termInDays);
    }

    private void validateRequest(Long clientId, BigDecimal amount, int termInDays) {
        clientService.checkClient(clientId);
        validateAmount(amount);
        validateTerm(termInDays);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(maxPossibleAmount) > 0 || amount.compareTo(BigDecimal.ZERO) <= 0) {
            String message = String.format("Requested amount should be greater than 0 or equal or less to max possible amount. (Max possible amount is %s EUR)", maxPossibleAmount.toString());
            throw new LoanException(message);
        }
    }

    private void validateTerm(int termInDays) {
        if (termInDays > maxTermDays || termInDays <= 0) {
            String message = String.format("Requested term should be greater than 0 or equal or less to max term. (Max term is %s days)", maxTermDays);
            throw new LoanException(message);
        }
    }

    private void analyzeRiskIfRequired(Long clientId, BigDecimal amount, String clientIp) {
        if (isAnalysisRequired(clientId, amount, clientIp)) {
            riskService.analyze(clientId);
        }
    }

    private boolean isAnalysisRequired(Long clientId, BigDecimal amount, String clientRemoteAddress) {
        boolean isRiskTime = isRiskTime(LocalTime.now(), stringTimeToLocalTime(riskTimeFrom), stringTimeToLocalTime(riskTimeTo));
        boolean isMaxAmount = amount.equals(maxPossibleAmount);
        long countOfRequests = loanRequestRepository.getCountOfRequestsWithTheSameIp(clientId, clientRemoteAddress);
        return (isRiskTime && isMaxAmount) || countOfRequests >= maxApplicationsPerDay;
    }

    @VisibleForTesting
    protected boolean isRiskTime(LocalTime time, LocalTime riskTimeFrom, LocalTime riskTimeTo) {
        return DateUtils.isTimeBetween(time, riskTimeFrom, riskTimeTo);
    }

    @VisibleForTesting
    protected LocalTime stringTimeToLocalTime(String time) {
        checkNotNull(time, "'time' is undefined");
        checkState(!time.isEmpty(), "Time string is empty");

        List<String> timeValues = Lists.newArrayList(time.split(":"));

        checkState(timeValues.size() == 2, String.format("Time string has wrong format '%s'. Format should be 'HH:MM'", time));
        int hours = Integer.parseInt(timeValues.get(0));
        int minutes = Integer.parseInt(timeValues.get(1));
        return LocalTime.of(hours, minutes);
    }

    private void createAndSaveLoanRequest(Long clientId, String clientRemoteAddress) {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setClientId(clientId);
        loanRequest.setIp(clientRemoteAddress);
        loanRequest.setDate(new Date());

        loanRequestRepository.save(loanRequest);
    }
}
