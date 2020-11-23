package com.revature.amazon.model;

import com.revature.amazon.model.Role;

public class User {

    private int id;
    private String email;
    private String password;
    private int balance;
    private Role role;

    public User() {
        super();
    }

    public User(int id,String email, String password, int balance, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return this.balance;
    }

    public void addBalance(int amt) {
        this.balance += amt;
    }

    public void subBalance(int amt) {
        this.balance -= amt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}