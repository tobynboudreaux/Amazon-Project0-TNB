package com.revature.amazon.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public Order findOrder() throws IOException {
        if (this.requestKey != null) {
            return orderDB.getOrder(Integer.parseInt(requestValue));

        } else {
            throw new IOException("Invalid Search Option");
        }
}

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = orderDB.getAllOrders();
        return orders;
    }
}