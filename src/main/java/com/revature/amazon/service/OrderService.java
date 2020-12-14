package com.revature.amazon.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

import com.revature.amazon.db.OrderDB;
import com.revature.amazon.model.Order;

public class OrderService {
    private String requestKey, requestValue;
    private OrderDB orderDB;
    
    public OrderService() {
        this.orderDB = new OrderDB();

    }

    public OrderService(OrderDB orderDB) {
        this.orderDB = new OrderDB();

    }

    public OrderService(String requestKey, String requestValue) {
        this.orderDB = new OrderDB();
        this.requestKey = requestKey;
        this.requestValue = requestValue;

    }

    public Order findOrder(int getID) throws IOException {
        return orderDB.getOrder(getID);

}

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = orderDB.getAllOrders();
        return orders;

    }

    public Order shipOrder(int id) {
        Order order = orderDB.getOrder(id);
        return orderDB.placeOrder(order, id);
    }

    public Order recallOrder(int id) {
        Order order = orderDB.getOrder(id);
        return orderDB.recallOrder(order, id);
    }

    public Order createOrder(int buyerID) {
        return orderDB.createOrder(buyerID);
    }

    public Order addItemToOrder(int orderID, int itemID) {
        return orderDB.addItemToOrder(orderID, itemID);
    }

    public Order removeItemFromOrder(int orderID, int itemID) {
        return orderDB.removeItemFromOrder(orderID, itemID);
    }

    public String deleteOrder(int orderID) {
        return orderDB.deleteOrder(orderID);
    }
}