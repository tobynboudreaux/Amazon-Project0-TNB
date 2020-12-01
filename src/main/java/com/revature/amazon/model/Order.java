package com.revature.amazon.model;

import com.revature.amazon.model.User;
import com.revature.amazon.model.Item;

import java.util.ArrayList;

public class Order {
    private int id;
    private boolean isShipped;
    private User buyer;
    private ArrayList<Item> items;

    public Order() {
        super();

    }

    public Order(int id, boolean isShipped, User buyer) {
        this.id = id;
        this.isShipped = isShipped;
        this.buyer = buyer;

    }
    
    public Order(int id, boolean isShipped, ArrayList<Item> items, User buyer) {
        this.id = id;
        this.isShipped = isShipped;
        this.buyer = buyer;
        this.items = items;

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

    public User getBuyer() {
        return this.buyer;

    }

    public ArrayList<Item> getItems() {
        return this.items;

    }
}