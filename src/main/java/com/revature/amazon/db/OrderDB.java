package com.revature.amazon.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import com.revature.amazon.model.Order;
import com.revature.amazon.model.User;
import com.revature.amazon.model.Item;
import com.revature.amazon.model.Role;
import com.revature.amazon.util.JDBCUtility;

public class OrderDB {

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList();
        String getOrders = "SELECT * FROM orders o INNER JOIN users u ON o.buyer_id = u.id INNER JOIN roles r ON u.role_id = r.id";

        try (Connection connection = JDBCUtility.getConnection()) {
            ResultSet rsOrder = connection.createStatement().executeQuery(getOrders);

            while (rsOrder.next()) {
                int orderId = rsOrder.getInt(1);
                boolean isShipped = rsOrder.getBoolean(2);
                int buyerId = rsOrder.getInt(3);
                String uEmail = rsOrder.getString(5);
                String uPassword = rsOrder.getString(6);
                int uBalance = rsOrder.getInt(7);
                int uRole_Id = rsOrder.getInt(8);
                String uRoleName = rsOrder.getString(10);

                String getOrderItems = "SELECT * FROM orderitems oi INNER JOIN orders o ON oi.orderid = o.id INNER JOIN items i ON oi.itemid = i.id INNER JOIN users ui ON i.seller_id = ui.id INNER JOIN roles r ON ui.role_id = r.id WHERE o.id = " + orderId + ";";
                ArrayList<Item> items = new ArrayList();
                ResultSet rs = connection.createStatement().executeQuery(getOrderItems);

                while (rs.next()) {
                    int itemId = rs.getInt(7);
                    String name = rs.getString(8);
                    int price = rs.getInt(9);
                    String description = rs.getString(10);
                    int sellerId = rs.getInt(11);
                    int uId1 = rs.getInt(12);
                    String uEmail1 = rs.getString(13);
                    String uPassword1 = rs.getString(14);
                    int uBalance1 = rs.getInt(15);
                    int uRole_Id1 = rs.getInt(16);
                    String uRoleName1 = rs.getString(18);
    
                    items.add(new Item(itemId, name, price, description, new User(uId1, uEmail1, uPassword1, uBalance1, new Role(uRole_Id1, uRoleName1))));
    
                }

                orders.add(new Order(orderId, isShipped, items, new User(buyerId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName))));
           
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return orders;
    }

    public Order getOrder(int id) {
        Order order = new Order();
        ArrayList<Item> items = new ArrayList();
        String getOrderItems = "SELECT * FROM orderitems oi INNER JOIN orders o ON oi.orderid = o.id INNER JOIN items i ON oi.itemid = i.id INNER JOIN users ui ON i.seller_id = ui.id INNER JOIN roles r ON ui.role_id = r.id WHERE o.id = " + id + ";";
        String getOrders = "SELECT * FROM orders o INNER JOIN users u ON o.buyer_id = u.id INNER JOIN roles r ON u.role_id = r.id WHERE o.id = " + id + ";";

        try (Connection connection = JDBCUtility.getConnection()) {
            ResultSet rs = connection.createStatement().executeQuery(getOrderItems);

            while (rs.next()) {
                int itemId = rs.getInt(7);
                String name = rs.getString(8);
                int price = rs.getInt(9);
                String description = rs.getString(10);
                int sellerId = rs.getInt(11);
                int uId = rs.getInt(12);
                String uEmail = rs.getString(13);
                String uPassword = rs.getString(14);
                int uBalance = rs.getInt(15);
                int uRole_Id = rs.getInt(16);
                String uRoleName = rs.getString(18);

                items.add(new Item(itemId, name, price, description, new User(uId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName))));

            }

            ResultSet rsOrder = connection.createStatement().executeQuery(getOrders);

            while (rsOrder.next()) {
                int orderId = rsOrder.getInt(1);
                boolean isShipped = rsOrder.getBoolean(2);
                int buyerId = rsOrder.getInt(3);
                String uEmail = rsOrder.getString(5);
                String uPassword = rsOrder.getString(6);
                int uBalance = rsOrder.getInt(7);
                int uRole_Id = rsOrder.getInt(8);
                String uRoleName = rsOrder.getString(10);

                order = new Order(orderId, isShipped, items, new User(buyerId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName)));
            
            }

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
       
        }

        return order;
    }
}