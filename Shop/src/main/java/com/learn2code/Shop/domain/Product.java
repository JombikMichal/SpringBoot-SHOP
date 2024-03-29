package com.learn2code.Shop.domain;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Product {
    @Nullable
    private Integer id;
    @NonNull
    private int merchant_id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private double price;
    @Nullable
    private Timestamp createdAt;
    @NonNull
    private int available;

    public Product(){}

    public Product(int merchant_id, String name, String description, double price, int available) {
        this.merchant_id = merchant_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    @Nullable
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return merchant_id == product.merchant_id &&
                Double.compare(product.price, price) == 0 &&
                available == product.available &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                createdAt.getTime() == product.createdAt.getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchant_id, name, description, price, createdAt, available);
    }
}
