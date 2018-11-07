package com.cdis.microservice.example.basket.model;

import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@ToString
public class BasketItem{

    @Id
    private Long itemId;

    private double price;
    private int quantity;
    private boolean paid;

    public BasketItem() {
    }

    public BasketItem(Long itemId, double price, int quantity, boolean paid) {
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
        this.paid = paid;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
