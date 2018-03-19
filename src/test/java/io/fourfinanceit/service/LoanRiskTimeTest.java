package io.fourfinanceit.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

@RunWith(SpringRunner.class)
public class LoanRiskTimeTest {

    @InjectMocks
    private LoanRequestServiceImpl loanRequestService;

    @Test
    public void riskTimeIsTrue() {
        Assert.assertTrue(isRiskTime("01:23"));
    }

    @Test
    public void riskTimeIsFalseWhenTimeIsBeforeRiskTime() {
        Assert.assertFalse(isRiskTime("23:59"));
    }

    @Test
    public void riskTimeIsFalseWhenTimeIsAfterRiskTime() {
        Assert.assertFalse(isRiskTime("08:01"));
    }

    private boolean isRiskTime(String time) {
        LocalTime current = loanRequestService.stringTimeToLocalTime(time);
        LocalTime from = loanRequestService.stringTimeToLocalTime("00:00");
        LocalTime to = loanRequestService.stringTimeToLocalTime("08:00");
        return loanRequestService.isRiskTime(current, from, to);
    }

}
