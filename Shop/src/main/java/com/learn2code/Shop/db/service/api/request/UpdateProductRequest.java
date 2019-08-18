package com.learn2code.Shop.db.service.api.request;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class UpdateProductRequest {
    @NonNull
    private String name;
    @NonNull
    private String descrition;
    @NonNull
    private double price;
    @NonNull
    private int available;

    public UpdateProductRequest(String name, String descrition, double price, int available) {
        this.name = name;
        this.descrition = descrition;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getDescrition() {
        return descrition;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateProductRequest that = (UpdateProductRequest) o;
        return Double.compare(that.price, price) == 0 &&
                available == that.available &&
                Objects.equals(name, that.name) &&
                Objects.equals(descrition, that.descrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, descrition, price, available);
    }
}
