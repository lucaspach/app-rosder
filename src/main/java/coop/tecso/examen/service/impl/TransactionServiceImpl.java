package coop.tecso.examen.service.impl;

import coop.tecso.examen.model.Money;
import coop.tecso.examen.model.Overdraft;
import coop.tecso.examen.service.MathService;
import coop.tecso.examen.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private MathService mathService;

    @Override
    public boolean descriptionValidation(String description) {
        if (description.length() <= 200) return true;
        return false;
    }

    @Override
    public boolean overdraftValidation(BigDecimal balance, Money money) {
        switch (money.getId()) {
            // PESO
            case 1:
                // si el resultado es -1, se paso del descubierto
                balance = mathService.sumar(balance, Overdraft.PESO);
                if (balance.compareTo(BigDecimal.ZERO) < 0) return false;
                break;
            // DOLAR
            case 2:
                balance = mathService.sumar(balance, Overdraft.DOLAR);
                if (balance.compareTo(BigDecimal.ZERO) < 0) return false;
                break;
            // EURO
            case 3:
                balance = mathService.sumar(balance, Overdraft.EURO);
                if (balance.compareTo(BigDecimal.ZERO) < 0) return false;
                break;
        }

        return true;
    }


}
