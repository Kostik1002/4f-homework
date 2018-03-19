package io.fourfinanceit.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loans")
public class Loan extends BaseEntity {

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private List<LoanDetail> loanDetails;

    public Loan() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LoanDetail> getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(List<LoanDetail> loanDetails) {
        this.loanDetails = loanDetails;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + getId() +
                ", amount=" + amount +
                ", clientId=" + clientId +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
