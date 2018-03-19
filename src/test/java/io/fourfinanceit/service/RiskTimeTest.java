package io.fourfinanceit.service;

import io.fourfinanceit.BaseTest;
import io.fourfinanceit.exceptions.RiskException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RiskTimeTest extends BaseTest {

    @Autowired
    private RiskService riskService;

    @Test
    public void riskAnalysisApproved() {
        riskService.analyze(1L);
    }

    @Test(expected = RiskException.class)
    public void riskAnalysisRejected() {
        riskService.analyze(2L);
    }
}
