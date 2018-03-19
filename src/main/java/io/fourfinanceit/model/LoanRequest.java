package io.fourfinanceit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "loan_requests")
public class LoanRequest extends BaseEntity {

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "date", nullable = false)
    private Date date;

    public LoanRequest() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "clientId=" + clientId +
                ", ip='" + ip + '\'' +
                ", date=" + date +
                '}';
    }
}
