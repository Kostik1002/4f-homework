package io.fourfinanceit.web;

import io.fourfinanceit.service.LoanRequestService;
import io.fourfinanceit.web.model.ExtendLoan;
import io.fourfinanceit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "loans")
public class LoanController {

    @Autowired
    private LoanRequestService loanRequestService;

    @Autowired
    private LoanService loanService;

    @PostMapping
    public void createLoan(@RequestParam(value = "clientId") Long clientId,
                           @RequestParam(value = "amount") BigDecimal amount,
                           @RequestParam(value = "termInDays") int termInDays,
                           HttpServletRequest request) {
        loanRequestService.requestLoan(clientId, amount, termInDays, request.getRemoteAddr());
    }

    @PutMapping
    public void extendLoan(@RequestBody ExtendLoan extendLoan) {
        loanService.extendLoan(extendLoan.getClientId(), extendLoan.getLoanId(), extendLoan.getDays());
    }

}
