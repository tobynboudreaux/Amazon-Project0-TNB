package com.revature.amazon.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import com.revature.amazon.util.JDBCUtility;

public class SetupDB {

    public String setupData() {

        String createUsers = "CREATE TABLE IF NOT EXISTS users ( "
            + "id int NOT NULL,"
            + "email TEXT NOT NULL,"
            + "password TEXT NOT NULL,"
            + "balance TEXT NOT NULL,"
            + "role_id int NOT NULL,"
            + "PRIMARY KEY(id),"
            + "FOREIGN KEY(role_id) REFERENCES roles(id)"
            + ")";
        String createOrders = "CREATE TABLE IF NOT EXISTS orders ( "
            + "id int NOT NULL,"
            + "isshipped boolean NOT NULL,"
            + "buyer_id int NOT NULL,"
            + "PRIMARY KEY(id),"
            + "FOREIGN KEY(buyer_id) REFERENCES users(id)"
            + ")";
        String createItems = "CREATE TABLE IF NOT EXISTS items ( "
            + "id int NOT NULL,"
            + "name TEXT NOT NULL,"
            + "price int NOT NULL,"
            + "description TEXT NOT NULL,"
            + "seller_id int NOT NULL,"
            + "PRIMARY KEY(id)"
            + ")";
        String createOrderItems = "CREATE TABLE IF NOT EXISTS orderitems ( " 
            + "id int NOT NULL,"
            + "orderid int NOT NULL,"
            + "itemid int NOT NULL,"
            + "PRIMARY KEY(id),"
            + "FOREIGN KEY(orderid) REFERENCES orders(id),"
            + "FOREIGN KEY(itemid) REFERENCES items(id)"
            + ")";
        String createRoles = "CREATE TABLE IF NOT EXISTS roles ( "
            + "id int NOT NULL,"
            + "role TEXT NOT NULL,"
            + "PRIMARY KEY(id)"
            + ")";

        try (Connection connection = JDBCUtility.getConnection()) {

            connection.createStatement().executeUpdate(createRoles);
            connection.createStatement().executeUpdate(createUsers);
            connection.createStatement().executeUpdate(createItems);
            connection.createStatement().executeUpdate(createOrders);
            connection.createStatement().executeUpdate(createOrderItems);

            return "Database setup successfully";
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return "Database was not setup";
    }
}
