package coop.tecso.examen.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "money")
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Money() {
    }

    public Money(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
