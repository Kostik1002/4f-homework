package io.fourfinanceit.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<Loan> loans;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<LoanRequest> loanRequests;

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public String getStatus() {
        return status;
    }

    public List<LoanRequest> getLoanRequests() {
        return loanRequests;
    }

    public void setLoanRequests(List<LoanRequest> loanRequest) {
        this.loanRequests = loanRequest;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +'\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
