package com.revature.amazon.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.amazon.db.ItemDB;
import com.revature.amazon.model.Item;

public class ItemService {

    private String requestKey, requestValue;
    private ItemDB itemDB;
    
    public ItemService() {
        this.itemDB = new ItemDB();    
    }

    public ItemService(ItemDB itemDB) {
        this.itemDB = new ItemDB();
    }

    public ItemService(String requestKey, String requestValue) {
        this.itemDB = new ItemDB();
        this.requestKey = requestKey;
        this.requestValue = requestValue;
    }

    public Item findItem() throws IOException {
        if (this.requestKey != null) {
            return itemDB.getItem(Integer.parseInt(requestValue));

        } else {
            throw new IOException("Invalid Search Option");
        }
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = itemDB.getAllItems();
        return items;
    }

    public Item updateItemPrice() throws IOException {
        if (this.requestKey != null) {
            return itemDB.updateItemPrice(Integer.parseInt(requestKey), Integer.parseInt(requestValue));
        } else {
            throw new IOException("Invalid Option. Items can only update price");
        }
    }

    public Item createItem() throws IOException {
        if (this.requestKey != null) {
            int id = Integer.parseInt(requestValue.split(",")[0]);
            String name = requestValue.split(",")[1].substring(3);
            int price = Integer.parseInt(requestValue.split(",")[2].substring(3));
            String description = requestValue.split(",")[3].substring(3);
            int seller_id = Integer.parseInt(requestValue.split(",")[4].substring(3));
            System.out.println(description);
            return itemDB.createItem(id, name, price, description, seller_id);

        } else {
            throw new IOException("Invalid number of args");
        }
    }

    public ArrayList<Item> deleteItem() throws IOException {
        if (this.requestKey != null) {
            ArrayList<Item> items = itemDB.deleteItem(Integer.parseInt(requestValue));
            return items;
        } else {
            throw new IOException("Item not found");
        }
    }
}