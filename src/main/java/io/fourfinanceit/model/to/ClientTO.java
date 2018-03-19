package io.fourfinanceit.model.to;

import io.fourfinanceit.model.Loan;

import java.util.List;

public class ClientTO {

    private String name;
    private String surname;
    private String phone;

    private List<LoanTO> loans;

    public ClientTO() {
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

    public List<LoanTO> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanTO> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "ClientTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", loans=" + loans +
                '}';
    }
}
