package io.fourfinanceit.model.to;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LoanTO {

    private Date loanDate;
    private BigDecimal amount;
    private BigDecimal total;
    private String status;
    private List<LoanDetailTO> details;

    public LoanTO() {
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LoanDetailTO> getDetails() {
        return details;
    }

    public void setDetails(List<LoanDetailTO> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "LoanTO{" +
                "loanDate=" + loanDate +
                ", amount=" + amount +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }
}
