package coop.tecso.examen.dto;

import coop.tecso.examen.model.PhysicalPerson;
import coop.tecso.examen.model.LegalPerson;

import java.util.List;

public class CustomerDTO {
    private int count;
    private List<PhysicalPerson> personasFisicas;
    private List<LegalPerson> personasJuridicas;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PhysicalPerson> getPersonasFisicas() {
        return personasFisicas;
    }

    public void setPersonasFisicas(List<PhysicalPerson> personasFisicas) {
        this.personasFisicas = personasFisicas;
    }

    public List<LegalPerson> getPersonasJuridicas() {
        return personasJuridicas;
    }

    public void setPersonasJuridicas(List<LegalPerson> personasJuridicas) {
        this.personasJuridicas = personasJuridicas;
    }
}
