package com.revature.amazon.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.amazon.db.UserDB;
import com.revature.amazon.model.User;

public class UserService {

    private String requestKey, requestValue;
    private UserDB userDB;
    
    public UserService() {
        this.userDB = new UserDB();    
    }

    public UserService(UserDB userDB) {
        this.userDB = new UserDB();
    }

    public UserService(String requestKey, String requestValue) {
        this.userDB = new UserDB();
        this.requestKey = requestKey;
        this.requestValue = requestValue;
    }

    public User findUser() throws IOException {
            if (this.requestKey != null) {
                return userDB.getUser(Integer.parseInt(requestValue));

            } else {
                throw new IOException("Invalid Search Option");
            }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = userDB.getAllUsers();
        return users;
    }

    public User updateUserBalance() throws IOException {
        if (this.requestKey != null) {
            return userDB.updateUserBalance(Integer.parseInt(requestKey), Integer.parseInt(requestValue));
        } else {
            throw new IOException("Invalid Option. Users can only update balance");
        }
    }

    public User createUser() throws IOException {
        if (this.requestKey != null) {
            int id = Integer.parseInt(requestValue.split(",")[0]);
            String email = requestValue.split(",")[1].substring(3);
            String password = requestValue.split(",")[2].substring(3);
            int balance = Integer.parseInt(requestValue.split(",")[3].substring(3));
            int role_id = Integer.parseInt(requestValue.split(",")[4].substring(3));
            System.out.println(email);
            return userDB.createUser(id, email, password, balance, role_id);

        } else {
            throw new IOException("Invalid number of args");
        }
    }

    public ArrayList<User> deleteUser() throws IOException {
        if (this.requestKey != null) {
            ArrayList<User> users = userDB.deleteUser(Integer.parseInt(requestValue));
            return users;
        } else {
            throw new IOException("User not found");
        }
    }
}