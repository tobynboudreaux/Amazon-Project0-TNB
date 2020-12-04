package com.revature.amazon.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import com.revature.amazon.model.Role;
import com.revature.amazon.model.User;
import com.revature.amazon.util.JDBCUtility;

public class UserDB {

    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList();
        String sqlQuery = "SELECT * FROM users u INNER JOIN roles r ON u.role_id = r.id";

        try (Connection connection = JDBCUtility.getConnection()) {

            ResultSet rs = connection.createStatement().executeQuery(sqlQuery);

            while (rs.next()) {

                int userId = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                int balance = rs.getInt(4);
                int roleId = rs.getInt(5);
                String roleName = rs.getString(7);

                users.add(new User(userId, email, password, balance, new Role(roleId, roleName)));
            }

            return users;
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }

    public User getUser(int resultValue) {

        User user = new User();
        String sqlQuery = "SELECT * FROM users u INNER JOIN roles r ON u.role_id = r.id WHERE u.id = " + resultValue + ";";
        
		try (Connection connection = JDBCUtility.getConnection()) {

            ResultSet rs = connection.createStatement().executeQuery(sqlQuery);

            while ( rs.next() ) {
                int userId = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                int balance = rs.getInt(4);
                int roleId = rs.getInt(5);
                String roleName = rs.getString(7);

                user = new User(userId, email, password, balance, new Role(roleId, roleName));
            }
              
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(UserDB.class);
			logger.debug(e.getMessage());
        }
        return user;
    }

    public User editUser(int editID, int id, String email, String password, int balance, int role_id) {

        User user = new User();
        String sqlQuery = "UPDATE users set id = ?, email = ?, password = ?, balance = ?, role_id = ? WHERE id = " + editID + ";";

        String sel = "SELECT * FROM users u INNER JOIN roles r ON u.role_id = r.id WHERE u.id = " + id + ";";

        try (Connection connection = JDBCUtility.getConnection()) {
            
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, balance);
            pstmt.setInt(5, role_id);

            pstmt.executeUpdate();

            ResultSet rs = connection.createStatement().executeQuery(sel);

            while ( rs.next() ) {
                int userId1 = rs.getInt(1);
                String email1 = rs.getString(2);
                String password1 = rs.getString(3);
                int balance1 = rs.getInt(4);
                int roleId1 = rs.getInt(5);
                String roleName1 = rs.getString(7);

                user = new User(userId1, email1, password1, balance1, new Role(roleId1, roleName1));
            }

        } catch (SQLException e) {
			Logger logger = Logger.getLogger(UserDB.class);
			logger.debug(e.getMessage());
        }
        return user;
    }

    public User createUser(int id, String email, String password, int balance, int role_id) {

        User user = new User();
        String sqlQuery = "INSERT INTO users (id, email, password, balance, role_id) " 
                        + "VALUES (?, ?, ?, ?, ?)";

        String sel = "SELECT * FROM users u INNER JOIN roles r ON u.role_id = r.id WHERE u.id = " + id + ";";
                    
        try (Connection connection = JDBCUtility.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, balance);
            pstmt.setInt(5, role_id);

            pstmt.executeUpdate();

            ResultSet rs = connection.createStatement().executeQuery(sel);

            while ( rs.next() ) {
                int userId1 = rs.getInt(1);
                String email1 = rs.getString(2);
                String password1 = rs.getString(3);
                int balance1 = rs.getInt(4);
                int roleId1 = rs.getInt(5);
                String roleName1 = rs.getString(7);

                user = new User(userId1, email1, password1, balance1, new Role(roleId1, roleName1));
            }
            
        } catch (SQLException e) {
			Logger logger = Logger.getLogger(UserDB.class);
			logger.debug(e.getMessage());
        }
        return user;
    }

    public ArrayList<User> deleteUser(int id) {

        ArrayList<User> users = new ArrayList();
        String del = "DELETE FROM users WHERE ID = " + id + ";";
        String sqlQuery = "SELECT * FROM users u INNER JOIN roles r ON u.role_id = r.id";

        try (Connection connection = JDBCUtility.getConnection()) {

            connection.createStatement().executeUpdate(del);
            ResultSet rs = connection.createStatement().executeQuery(sqlQuery);

            while (rs.next()) {

                int userId = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                int balance = rs.getInt(4);
                int roleId = rs.getInt(5);
                String roleName = rs.getString(7);

                users.add(new User(userId, email, password, balance, new Role(roleId, roleName)));
            }

            return users;
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }
    
    public Boolean validEmail(String email, String password) {
		String sqlQuery = "SELECT * FROM users WHERE email = ? and password = ? LIMIT 1";

		try (Connection connection = JDBCUtility.getConnection()) {

			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			Logger logger = Logger.getLogger(UserDB.class);
			logger.debug(e.getMessage());
		}
		return false;
	}

}