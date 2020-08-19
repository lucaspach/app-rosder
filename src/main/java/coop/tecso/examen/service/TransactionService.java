package coop.tecso.examen.service;

import coop.tecso.examen.model.Money;

import java.math.BigDecimal;


public interface TransactionService {
    boolean descriptionValidation(String description);
    boolean overdraftValidation(BigDecimal balance, Money money);


}
