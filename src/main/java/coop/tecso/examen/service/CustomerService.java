package coop.tecso.examen.service;

public interface CustomerService {

    public boolean firstNameValidation(String firstName);
    public boolean lastNameValidation(String lastName);
    public boolean businessNameValidation(String businessName);
    public boolean rutValidation(long rut);
    //public boolean personaTypeValidation(String personaType);
}
