package coop.tecso.examen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Year;

@Entity (name = "legal_person")
public class LegalPerson extends Customer {

    private String businessName;
    private Year foundationYear;


    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Year getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Year foundationYear) {
        this.foundationYear = foundationYear;
    }

    @Override
    public String toString() {
        return "LegalPerson {" +
                "BusinessName='" + businessName + '\'' +
                ", foundationYear=" + foundationYear +
                '}';
    }
}
