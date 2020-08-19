package coop.tecso.examen.service.impl;


import coop.tecso.examen.repository.PhysicalPersonRepository;
import coop.tecso.examen.repository.LegalPersonRepository;
import coop.tecso.examen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    PhysicalPersonRepository physicalPersonRepository;
    @Autowired
    LegalPersonRepository legalPersonRepository;

    @Override
    public boolean firstNameValidation(String firstName) {
        if (firstName.length() <= 80) return true;
        return false;
    }

    @Override
    public boolean lastNameValidation(String lastName) {
        if (lastName.length() <= 250) return true;
        return false;
    }

    @Override
    public boolean businessNameValidation(String businessName) {
        if (businessName.length() <= 200) return true;
        return false;
    }

    @Override
    public boolean rutValidation(long rut) {
        if (!physicalPersonRepository.existsByRut(rut) && !legalPersonRepository.existsByRut(rut)) return true;
        return false;
    }

    /*@Override
    public boolean personaTypeValidation(String personaType) {
        boolean check = false;
        for (PersonaType myPersona : PersonaType.values()) {
            if (myPersona.toString().equals(personaType)) check = true;
        }

        return check;
    }*/
}
