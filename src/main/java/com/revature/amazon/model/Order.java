package com.revature.amazon.model;

import com.revature.amazon.model.User;
import com.revature.amazon.model.Item;

import java.util.ArrayList;

public class Order {
    private int id;
    private boolean isShipped;
    private User buyer;

    public Order() {
        super();

    }

    public Order(int id, boolean isShipped, User buyer) {
        this.id = id;
        this.isShipped = isShipped;
        this.buyer = buyer;

    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;

    }

    public void ship() {
        this.isShipped = true;

    }

    public void recall() {
        this.isShipped = false;

    }
}