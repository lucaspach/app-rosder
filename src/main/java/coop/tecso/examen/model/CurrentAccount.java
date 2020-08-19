package coop.tecso.examen.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity (name = "current_account")
public class CurrentAccount {
    @Id
    private long accountNumber;

    @ManyToOne()
    @JoinColumn
    private Money money;

    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
    //@JoinColumn()
    private List<Transaction> transactions;

    private BigDecimal balance;

    public CurrentAccount() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public CurrentAccount(long accountNumber, Money money, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.money = money;
        this.balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "CurrentAccount{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", money=" + money +
                '}';
    }
}
