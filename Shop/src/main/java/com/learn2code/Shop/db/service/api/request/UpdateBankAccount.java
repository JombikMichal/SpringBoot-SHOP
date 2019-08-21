package com.learn2code.Shop.db.service.api.request;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class UpdateBankAccount {
    @NonNull
    private double account_balance;

    public UpdateBankAccount(double account_balance) {
        this.account_balance = account_balance;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBankAccount that = (UpdateBankAccount) o;
        return Double.compare(that.account_balance, account_balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_balance);
    }
}
