package coop.tecso.examen.dto;

import coop.tecso.examen.model.CurrentAccount;

import java.util.List;

public class AccountDTO {
    private int count;
    private List<CurrentAccount> currentAccounts;

    public AccountDTO() {
    }

    public AccountDTO(int count, List<CurrentAccount> currentAccounts) {
        this.count = count;
        this.currentAccounts = currentAccounts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CurrentAccount> getCurrentAccounts() {
        return currentAccounts;
    }

    public void setCurrentAccounts(List<CurrentAccount> currentAccounts) {
        this.currentAccounts = currentAccounts;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "count=" + count +
                ", currentAccounts=" + currentAccounts +
                '}';
    }
}
