package com.learn2code.Shop.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class Customer {
    @Nullable
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @Nullable
    private Integer age;
    @Nullable
    private String phoneNumber;

    //preco je potrebne explicitne vytvorit bezparametricky konstruktor - chapem ze ked sme spravili parametricky konstruktor tak sa nam implicitne uz nevytvori bezparametricky ale naco na to neskor bude?
    //robi sa to kvoli testom?
    public Customer() {
    }

    public Customer(String name, String surname, String email, String address, @Nullable Integer age, @Nullable String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public String getName() { return name; }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }

    @Nullable
    public String getPhoneNumber() { return phoneNumber; }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(@Nullable Integer age) {
        this.age = age;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, address, age, phoneNumber);
    }
}
