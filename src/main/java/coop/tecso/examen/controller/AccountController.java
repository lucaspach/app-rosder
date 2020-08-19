package coop.tecso.examen.controller;

import coop.tecso.examen.dto.AccountDTO;
import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.repository.CurrentAccountRepository;
import coop.tecso.examen.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/currentaccount")
public class AccountController {

    @Autowired
    private CurrentAccountRepository currentAccountRepository;
    @Autowired
    private AccountService accountService;

    @PostMapping("sign-up")
    public ResponseEntity signUp(@RequestBody CurrentAccount currentAccount) {
        try {
            if (currentAccount.getAccountNumber() <= 0)
                return ResponseEntity.status(400).body("Account number must be a positive number");
            // logica de negocio
            if (!accountService.accountNumberValidation(currentAccount.getAccountNumber()))
                return ResponseEntity.status(400).body("This id account: "
                        + currentAccount.getAccountNumber() + " already exits");
            // lo guarda correctamente en la base (2 decimales redondeados) y
            // lo modifico en el objeto para que se vea en el JSON
            currentAccountRepository.save(currentAccount);
            currentAccount.setBalance(currentAccount.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP));

            return ResponseEntity.status(201).body(currentAccount);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }

    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity deleteByAccountNumber(@PathVariable long accountNumber) {
        try {
            if (accountNumber <= 0) return ResponseEntity.status(400).body("Account number must be a positive number");
            // logica de negocio
            CurrentAccount currentAccount = currentAccountRepository.findByAccountNumber(accountNumber);
            if (currentAccount == null) return ResponseEntity.status(404).body("{}");
            if (!currentAccount.getTransactions().isEmpty())
                return ResponseEntity.status(400).body("Can't deleted an Account with Transactions");

            currentAccountRepository.delete(currentAccount);

            return ResponseEntity.ok("Account deleted");

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        try {
            List<CurrentAccount> currentAccounts = currentAccountRepository.findAll();
            if (currentAccounts.isEmpty()) return ResponseEntity.status(404).body("[]");


            return ResponseEntity.ok(currentAccounts);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity findByAccountNumber(@PathVariable long accountNumber) {
        try {
            CurrentAccount currentAccount = currentAccountRepository.findByAccountNumber(accountNumber);
            if (currentAccount == null) return ResponseEntity.status(404).body("{}");

            return ResponseEntity.ok(currentAccount);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }


}
