package io.fourfinanceit.model.to;

import java.util.Date;

public class LoanDetailTO {

    private Date extensionDate;
    private Date term;

    public LoanDetailTO() {
    }

    public Date getExtensionDate() {
        return extensionDate;
    }

    public void setExtensionDate(Date extensionDate) {
        this.extensionDate = extensionDate;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "LoanDetailTO{" +
                "extensionDate=" + extensionDate +
                ", term=" + term +
                '}';
    }
}
