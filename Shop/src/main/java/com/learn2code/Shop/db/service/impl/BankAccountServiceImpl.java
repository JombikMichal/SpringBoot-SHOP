package com.learn2code.Shop.db.service.impl;

import com.learn2code.Shop.db.repository.BankAccountRepository;
import com.learn2code.Shop.db.service.api.BankAccountService;
import com.learn2code.Shop.db.service.api.request.UpdateBankAccount;
import com.learn2code.Shop.domain.BankAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.getAll();
    }

    @Override
    public BankAccount get(int id) {
        return bankAccountRepository.get(id);
    }

    @Override
    public Integer add(BankAccount bankAccount) {
        return bankAccountRepository.add(bankAccount);
    }

    @Override
    public void update(int id, UpdateBankAccount request) {
        bankAccountRepository.update(id,request);
    }
}
