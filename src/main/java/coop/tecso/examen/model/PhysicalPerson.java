package coop.tecso.examen.model;

import javax.persistence.*;

@Entity (name = "physical_person")
public class PhysicalPerson extends Customer {

    private String firstName;
    private String lastName;
    private Long cc;


    public PhysicalPerson() {
    }

    public PhysicalPerson(String firstName, String lastName, Long cc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cc = cc;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getCc() {
        return cc;
    }

    public void setCc(Long cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "PhysicalPerson{" +
                "FirstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cc=" + cc +
                '}';
    }
}
