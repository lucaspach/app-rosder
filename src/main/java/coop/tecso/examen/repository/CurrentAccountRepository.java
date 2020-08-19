package coop.tecso.examen.repository;

import coop.tecso.examen.model.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {
    //@Query(value = "SELECT ca.*, m.name FROM CURRENT_ACCOUNT AS ca INNER JOIN MONEY AS m ON ca.money_id = m.id",
    //        nativeQuery = true)
    List<CurrentAccount> findAll();
    boolean existsByAccountNumber(long accountNumber);
    CurrentAccount findByAccountNumber(long accountNumber);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE CURRENT_ACCOUNT SET BALANCE = ?1 WHERE ACCOUNT_NUMBER = ?2", nativeQuery = true)
    int updateBalance(BigDecimal balance, long accountNumber);
}
