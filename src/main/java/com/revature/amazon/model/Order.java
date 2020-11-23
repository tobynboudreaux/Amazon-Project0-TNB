package com.revature.amazon.model;

import com.revature.amazon.model.User;
import com.revature.amazon.model.Item;

import java.util.ArrayList;

public class Order {

    private int id;
    private boolean isShipped;
    private int totalPrice;
    private ArrayList<Item> items;
    private User buyer;

    public Order() {
        super();
    }

    public Order(int id, boolean isShipped, int totalPrice, ArrayList<Item> items, User buyer) {
        this.id = id;
        this.isShipped = isShipped;
        this.totalPrice = totalPrice;
        this.items = items;
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

    public int getTotalPrice() {
        return this.totalPrice;
    }

    // public void total() {
    //     int total = 0;
    //     for(int i; i < items.length() + 1; i++) {
    //         total += items[i].price();
    //         this.totalPrice = total;
    //     }
    // }
    
    public ArrayList getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    // public void removeItem(Item item) {
    //     // need index of item to remove
    //     this.items.remove(item.index());
    // }

    // get seller/sellers from items
}