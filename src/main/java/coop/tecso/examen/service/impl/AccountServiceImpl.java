package coop.tecso.examen.service.impl;

import coop.tecso.examen.repository.CurrentAccountRepository;
import coop.tecso.examen.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    @Override
    public boolean accountNumberValidation(long accountNumber) {
        if (!currentAccountRepository.existsByAccountNumber(accountNumber)) return true;
        return false;
    }
}
