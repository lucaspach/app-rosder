package coop.tecso.examen.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "transactions_type")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /*@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();*/

    public TransactionType() {
    }

    public TransactionType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
