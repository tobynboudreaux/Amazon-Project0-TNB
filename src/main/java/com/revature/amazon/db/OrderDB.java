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
import com.revature.amazon.model.Role;
import com.revature.amazon.util.JDBCUtility;

public class OrderDB {

    public ArrayList<Order> getAllOrders() {

        ArrayList<Order> orders = new ArrayList();
        String sqlQuery = "SELECT * FROM orders o INNER JOIN users u ON o.buyer_id = u.id INNER JOIN roles r ON u.role_id = r.id";

        try (Connection connection = JDBCUtility.getConnection()) {

            ResultSet rs = connection.createStatement().executeQuery(sqlQuery);

            while (rs.next()) {

                int orderId = rs.getInt(1);
                boolean isShipped = rs.getBoolean(2);
                int buyerId = rs.getInt(3);
                String uEmail = rs.getString(5);
                String uPassword = rs.getString(6);
                int uBalance = rs.getInt(7);
                int uRole_Id = rs.getInt(8);
                String uRoleName = rs.getString(10);

                orders.add(new Order(orderId, isShipped, new User(buyerId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName))));
            }

            return orders;
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrder(int id) {

        Order order = new Order();
        String sqlQuery = "SELECT * FROM orders o INNER JOIN users u ON o.buyer_id = u.id INNER JOIN roles r ON u.role_id = r.id WHERE o.id = " + id + ";";

        try (Connection connection = JDBCUtility.getConnection()) {

            ResultSet rs = connection.createStatement().executeQuery(sqlQuery);

            while (rs.next()) {

                int orderId = rs.getInt(1);
                boolean isShipped = rs.getBoolean(2);
                int buyerId = rs.getInt(3);
                String uEmail = rs.getString(5);
                String uPassword = rs.getString(6);
                int uBalance = rs.getInt(7);
                int uRole_Id = rs.getInt(8);
                String uRoleName = rs.getString(10);

                order = new Order(orderId, isShipped, new User(buyerId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName)));
            }

            return order;
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return order;
    }
}