package coop.tecso.examen.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Customer {

    @Id
    private long rut;

    public long getRut() {
        return rut;
    }


}
