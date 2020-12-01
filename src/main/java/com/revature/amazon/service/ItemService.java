package com.revature.amazon.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

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
        if (this.requestKey.equals("id")) {
            return itemDB.getItem(Integer.parseInt(requestValue), "i.id");

        } else if (this.requestKey.equals("price")) {
            return itemDB.getItem(Integer.parseInt(requestValue), "i.price");

        } else if (this.requestKey.equals("seller")) {
            return itemDB.getItem(Integer.parseInt(requestValue), "u.id");

        } else {
            throw new IOException("Invalid Search Option");

        }

    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = itemDB.getAllItems();
        return items;

    }

    public Item editItem(int editID, JSONObject jsonObject) throws IOException {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        int price = jsonObject.getInt("price");
        int seller_id = jsonObject.getInt("seller_id");
        return itemDB.editItem(editID, id, name, price, description, seller_id);

    }

    public Item createItem(JSONObject jsonObject) throws IOException {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        int price = jsonObject.getInt("price");
        int seller_id = jsonObject.getInt("seller_id");
        return itemDB.createItem(id, name, price, description, seller_id);

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