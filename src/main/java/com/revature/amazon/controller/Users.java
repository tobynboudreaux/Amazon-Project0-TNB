package com.revature.amazon.controller;

import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.amazon.model.User;
import com.revature.amazon.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/users")
public class Users extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
            System.out.println("get request recieved @ /users");

            if (req.getQueryString() != null) {

                String requestKey = req.getQueryString().split("=")[0];
                String requestValue = req.getQueryString().split("=")[1];

                try {

                    User user = new UserService(requestKey, requestValue).findUser();
                    resp.getWriter().append(objectMapper.writeValueAsString(user));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                } catch (IOException e) {

                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Users.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                }
                    
            } else {

                try {

                    ArrayList<User> users = new UserService().getAllUsers();
                    resp.getWriter().append(objectMapper.writeValueAsString(users));
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                } catch (Exception e) {

                    resp.setStatus(400);
                    e.printStackTrace();
                }
            }
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("post request recieved @ /users");

            if (req.getQueryString() != null) {

                String requestKey = req.getQueryString().split("=")[0];
                String requestValue = req.getQueryString().split("=")[1];
                                
                try {
                    
                    User user = new UserService(requestKey, requestValue).createUser();
                    resp.getWriter().append(objectMapper.writeValueAsString(user));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                } catch (IOException e) {

                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Users.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                }
                    
            } else {
                resp.setStatus(400);

                System.out.println("User not found");
            }
        }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("put request recieved @ /users");

            if (req.getQueryString() != null) {

                String requestid = req.getQueryString().split("&")[0];
                String requestbalance = req.getQueryString().split("&")[1];
                String requestKey = requestid.split("=")[1];
                String requestValue = requestbalance.split("=")[1];
                
                try {

                    User user = new UserService(requestKey, requestValue).updateUserBalance();
                    resp.getWriter().append(objectMapper.writeValueAsString(user));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                } catch (IOException e) {

                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Users.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                }
                    
            } else {
                resp.setStatus(400);

                System.out.println("User not found");
            }
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("delete request recieved @ /users");

            if (req.getQueryString() != null) {

                String requestKey = req.getQueryString().split("=")[0];
                String requestValue = req.getQueryString().split("=")[1];
                
                try {

                    ArrayList<User> users = new UserService(requestKey, requestValue).deleteUser();
                    resp.getWriter().append(objectMapper.writeValueAsString(users));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                } catch (IOException e) {

                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Users.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                }
                    
            } else {
                resp.setStatus(400);

                System.out.println("User not found");
            }
        }
}