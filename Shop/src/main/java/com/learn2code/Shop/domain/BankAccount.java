package com.learn2code.Shop.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.Objects;

public class BankAccount {
    // na tejto triede alebo funkcionalite sa postupne ucim vsetky principy ako pri inych
    //co by mal mat bank account?
    // podla velkej teorie by mal mat kazdy clovek bank account ale v nasom pripade to ma zmysel len pri zakaznikovi lebo on je kupujuci
    //nemoze byt null lebo nema zmysel vytvarat ucet ked nemame pre koho
    @Nullable
    private Integer id;
    @NonNull
    private int customerId;
    // nebude to int ani number lebo cislo bankoveho uctu moze byt aj iban, ma nejaky tvar a obsahuje aj ine ako cisla
    @NonNull
    private String BankAccountNumber;
    //stav penazi na ucte - pri vytvarani uctu bude automaticky nastaveny na 0 nie na null
    @NonNull
    private double accountBalance;
    @NonNull
    private Timestamp createdAt;


    public BankAccount(){}
    public BankAccount(int customerId, String bankAccountNumber, double accountBalance, Timestamp createdAt) {
        this.customerId = customerId;
        BankAccountNumber = bankAccountNumber;
        this.accountBalance = accountBalance;
        this.createdAt = createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        BankAccountNumber = bankAccountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getBankAccountNumber() {
        return BankAccountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return customerId == that.customerId &&
                Double.compare(that.accountBalance, accountBalance) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(BankAccountNumber, that.BankAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, BankAccountNumber, accountBalance);
    }
}
