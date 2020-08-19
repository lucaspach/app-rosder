package coop.tecso.examen.model;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;


@Entity (name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn
    private CurrentAccount currentAccount;

    private Instant date;

    @ManyToOne()
    @JoinColumn
    private TransactionType transactionType;

    private String description;

    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(long id, CurrentAccount currentAccount, Instant Date, TransactionType transactionType, String description, BigDecimal amount) {
        this.id = id;
        this.currentAccount = currentAccount;
        this.date = Instant.now();
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(CurrentAccount currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", date=" + date +
                ", transactionsType=" + transactionType +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
