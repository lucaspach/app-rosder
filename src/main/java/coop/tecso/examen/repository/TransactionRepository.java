package coop.tecso.examen.repository;

import coop.tecso.examen.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

   List<Transaction> findByCurrentAccount_AccountNumberOrderByDateDesc(long accountNumber);
}
