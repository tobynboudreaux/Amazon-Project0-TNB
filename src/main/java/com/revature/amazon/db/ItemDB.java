package com.revature.amazon.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import com.revature.amazon.model.Item;
import com.revature.amazon.model.Order;
import com.revature.amazon.model.User;
import com.revature.amazon.model.Role;
import com.revature.amazon.util.JDBCUtility;
import com.revature.amazon.service.UserService;

public class ItemDB {

    public ArrayList<Item> getAllItems() {

        ArrayList<Item> items = new ArrayList();
        String sqlQuery = "SELECT * FROM items i INNER JOIN users u ON i.seller_id = u.id INNER JOIN roles r ON u.role_id = r.id";
        
        try (Connection connection = JDBCUtility.getConnection()) {

            ResultSet rs = connection.createStatement().executeQuery(sqlQuery);

            while (rs.next()) {

                int itemId = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String description = rs.getString(4);
                int sellerId = rs.getInt(5);
                int uId = rs.getInt(6);
                String uEmail = rs.getString(7);
                String uPassword = rs.getString(8);
                int uBalance = rs.getInt(9);
                int uRole_Id = rs.getInt(10);
                String uRoleName = rs.getString(12);

                items.add(new Item(itemId, name, price, description, new User(uId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName))));
            }

            return items;
        } catch (SQLException e) {

            Logger logger = Logger.getLogger(ItemDB.class);
            logger.debug(e.getMessage());
        }

        return items;
    }   

    public Item getItem(int value, String type) {

        Item item = new Item();

        String sqlQuery = "SELECT * FROM items i INNER JOIN users u ON i.seller_id = u.id INNER JOIN roles r ON u.role_id = r.id WHERE " + type + " = " + value + ";";
        
        try (Connection connection = JDBCUtility.getConnection()) {

            ResultSet rs = connection.createStatement().executeQuery(sqlQuery);

            while (rs.next()) {

                int itemId = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String description = rs.getString(4);
                int sellerId = rs.getInt(5);
                int uId = rs.getInt(6);
                String uEmail = rs.getString(7);
                String uPassword = rs.getString(8);
                int uBalance = rs.getInt(9);
                int uRole_Id = rs.getInt(10);
                String uRoleName = rs.getString(12);

                item = new Item(itemId, name, price, description, new User(uId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName)));
            }

            return item;
        } catch (SQLException e) {

            Logger logger = Logger.getLogger(ItemDB.class);
			logger.debug(e.getMessage());
        }

        return item;
    }

    public Item updateItemPrice(int id, int value) {

        Item item = new Item();
        String sqlQuery = "UPDATE items set price = " + value + " WHERE ID = " + id + ";";

        String sel = "SELECT * FROM items i INNER JOIN users u ON i.seller_id = u.id INNER JOIN roles r ON u.role_id = r.id WHERE i.id = " + id + ";";

        try (Connection connection = JDBCUtility.getConnection()) {
            
            connection.createStatement().executeUpdate(sqlQuery);
            ResultSet rs = connection.createStatement().executeQuery(sel);

            while (rs.next()) {

                int itemId = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String description = rs.getString(4);
                int sellerId = rs.getInt(5);
                int uId = rs.getInt(6);
                String uEmail = rs.getString(7);
                String uPassword = rs.getString(8);
                int uBalance = rs.getInt(9);
                int uRole_Id = rs.getInt(10);
                String uRoleName = rs.getString(12);

                item = new Item(itemId, name, price, description, new User(uId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName)));
            }
            
            return item;
        } catch (SQLException e) {
			Logger logger = Logger.getLogger(ItemDB.class);
			logger.debug(e.getMessage());
        }
        return item;
    }

    public Item createItem(int id, String name, int price, String description, int seller_id) {

        Item item = new Item();
        String sqlQuery = "INSERT INTO items (ID, NAME, PRICE, DESCRIPTION, SELLER_ID) " 
                        + "VALUES (?, ?, ?, ?, ?)";

        String sel = "SELECT * FROM items i INNER JOIN users u ON i.seller_id = u.id INNER JOIN roles r ON u.role_id = r.id WHERE i.id = " + id + ";";
                    
        try (Connection connection = JDBCUtility.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, price);
            pstmt.setString(4, description);
            pstmt.setInt(5, seller_id);

            pstmt.executeUpdate();

            ResultSet rs = connection.createStatement().executeQuery(sel);

            while (rs.next()) {

                int itemId1 = rs.getInt(1);
                String name1 = rs.getString(2);
                int price1 = rs.getInt(3);
                String description1 = rs.getString(4);
                int sellerId1 = rs.getInt(5);
                int uId1 = rs.getInt(6);
                String uEmail1 = rs.getString(7);
                String uPassword1 = rs.getString(8);
                int uBalance1 = rs.getInt(9);
                int uRole_Id1 = rs.getInt(10);
                String uRoleName1 = rs.getString(12);

                item = new Item(itemId1, name1, price1, description1, new User(uId1, uEmail1, uPassword1, uBalance1, new Role(uRole_Id1, uRoleName1)));
            }
            
            return item;
        } catch (SQLException e) {
			Logger logger = Logger.getLogger(ItemDB.class);
			logger.debug(e.getMessage());
        }
        return item;
    }

    public ArrayList<Item> deleteItem(int id) {

        ArrayList<Item> items = new ArrayList();
        String del = "DELETE FROM items WHERE ID = " + id + ";";
        String sel = "SELECT * FROM items i INNER JOIN users u ON i.seller_id = u.id INNER JOIN roles r ON u.role_id = r.id WHERE i.id = " + id + ";";

        try (Connection connection = JDBCUtility.getConnection()) {

            connection.createStatement().executeUpdate(del);
            ResultSet rs = connection.createStatement().executeQuery(sel);

            while (rs.next()) {

                int itemId = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String description = rs.getString(4);
                int sellerId = rs.getInt(5);
                int uId = rs.getInt(6);
                String uEmail = rs.getString(7);
                String uPassword = rs.getString(8);
                int uBalance = rs.getInt(9);
                int uRole_Id = rs.getInt(10);
                String uRoleName = rs.getString(12);

                items.add(new Item(itemId, name, price, description, new User(uId, uEmail, uPassword, uBalance, new Role(uRole_Id, uRoleName)))); 
            }

            return items;
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return items;
    }
    
}