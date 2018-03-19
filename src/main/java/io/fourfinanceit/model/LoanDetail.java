package io.fourfinanceit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "loan_details")
public class LoanDetail extends BaseEntity {

    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "term", nullable = false)
    private Date term;

    @Column(name = "status", nullable = false)
    private String status;

    public LoanDetail() {
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoanDetail{" +
                "id=" + getId() +
                " loanId=" + loanId +
                ", date=" + date +
                ", term=" + term +
                ", status=" + status +
                '}';
    }
}
