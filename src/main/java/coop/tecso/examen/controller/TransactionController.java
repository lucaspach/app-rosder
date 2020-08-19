package coop.tecso.examen.controller;

import coop.tecso.examen.model.Money;
import coop.tecso.examen.model.Transaction;
import coop.tecso.examen.repository.CurrentAccountRepository;
import coop.tecso.examen.repository.TransactionRepository;
import coop.tecso.examen.service.AccountService;
import coop.tecso.examen.service.MathService;
import coop.tecso.examen.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CurrentAccountRepository currentAccountRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private MathService mathService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity getTransactionXAccount(@PathVariable long accountNumber) {
        try {
            if (accountNumber <= 0) return ResponseEntity.status(400).body("Account number must be a positive number");

            List<Transaction> transactions = transactionRepository.findByCurrentAccount_AccountNumberOrderByDateDesc(accountNumber);

            return ResponseEntity.ok(transactions);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }


    @PostMapping("")
    public ResponseEntity addTransaction(@RequestBody Transaction transaction) {
        // , @PathVariable long accountNumber
        try {
            // traemos la cuenta
            //CurrentAccount currentAccount = currentAccountRepository.findByAccountNumber(accountNumber);
            long accountNumber = transaction.getCurrentAccount().getAccountNumber();
            // si la validación es verdadera es que es cliente no existe
            if (accountService.accountNumberValidation(accountNumber))
                return ResponseEntity.status(400).body("This Current Account: "
                        + accountNumber + " doesn't exits");

            // logica de negocio
            if (!transactionService.descriptionValidation(transaction.getDescription()))
                return ResponseEntity.status(400).body("Description maxlength: 200");

            // asignamos la fecha
            transaction.setDate(Instant.now());

            BigDecimal amount = transaction.getAmount();
            Money money = transaction.getCurrentAccount().getMoney();

            // debito
            if (transaction.getTransactionType().getId() == 1) {
                amount = amount.negate();
                BigDecimal balance = mathService.sumar(transaction.getCurrentAccount().getBalance(), amount);

                if (balance.compareTo(BigDecimal.ZERO) < 0 && !transactionService.overdraftValidation(balance, money))
                    return ResponseEntity.status(400).body("Denied. Can't create this transaction. Check your balance");
                // actualizamos el saldo de la cuenta
                //transaction.getCurrentAccount().setBalance(balance);
                System.out.println(balance.toString() + " " + accountNumber);
                currentAccountRepository.updateBalance(balance, accountNumber);

                transactionRepository.save(transaction);
            }
            // credito
            else if (transaction.getTransactionType().getId() == 2) {
                // actualizamos el saldo de la cuenta
                BigDecimal balance = mathService.sumar(transaction.getCurrentAccount().getBalance(), amount);
                currentAccountRepository.updateBalance(balance, accountNumber);
                transactionRepository.save(transaction);

            } else {
                return ResponseEntity.status(500).body("Sometimes went wrong with the type of transaction");
            }

            return ResponseEntity.ok(transaction);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

}
