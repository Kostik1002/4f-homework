package io.fourfinanceit.web.model;

public class ExtendLoan {

    private Long clientId;
    private Long loanId;
    private int days;

    public ExtendLoan() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "ExtendLoan{" +
                "clientId=" + clientId +
                ", loanId=" + loanId +
                ", days=" + days +
                '}';
    }
}
