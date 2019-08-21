package com.learn2code.Shop.db.service.api;

import com.learn2code.Shop.db.service.api.request.UpdateBankAccount;
import com.learn2code.Shop.domain.BankAccount;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BankAccountService {
    public List<BankAccount> getBankAccounts();
    @Nullable
    public BankAccount get(int id);
    @NonNull
    public Integer add(BankAccount bankAccount);
    public void update(int id, UpdateBankAccount request);
}
