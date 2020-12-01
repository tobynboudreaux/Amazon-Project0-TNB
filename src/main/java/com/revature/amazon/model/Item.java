package com.revature.amazon.model;

public class Item {
    private int id;
    private String name;
    private int price;
    private String description;
    private User seller;

    public Item() {
        super();

    }

    public Item(int id, String name, int price, String description, User seller) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.seller = seller;

    }

    public int getId() {
        return this.id;

    }

    public void setId(int id) {
        this.id = id;

    }

    public String getName() {
        return this.name;

    }

    public void setName(String name) {
        this.name = name;

    }

    public int getPrice() {
        return this.price;

    }

    public void setPrice(int price) {
        this.price = price;

    }

    public String getDescription() {
        return this.description;

    }

    public void setDescription(String description) {
        this.description = description;
        
    }
}