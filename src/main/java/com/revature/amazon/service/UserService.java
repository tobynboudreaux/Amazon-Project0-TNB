package com.revature.amazon.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

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

    public User findUser(int getID) throws IOException {
        return userDB.getUser(getID);

    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = userDB.getAllUsers();
        return users;

    }

    public User editUser(int editID, JSONObject jsonObject) throws IOException {
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        int balance = jsonObject.getInt("balance");
        int role_id = jsonObject.getInt("role_id");
        return userDB.editUser(editID, email, password, balance, role_id);
    
    }

    public ArrayList<User> createUser(JSONObject jsonObject) throws IOException {
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        int balance = jsonObject.getInt("balance");
        int role_id = jsonObject.getInt("role_id");
        return userDB.createUser(email, password, balance, role_id);
    
    }

    public ArrayList<User> deleteUser(int deleteID) throws IOException {
        ArrayList<User> users = userDB.deleteUser(deleteID);
        return users;

    }
}