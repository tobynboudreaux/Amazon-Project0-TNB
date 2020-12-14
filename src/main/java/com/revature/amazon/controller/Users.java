package com.revature.amazon.controller;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.HTTP;

import com.revature.amazon.model.User;
import com.revature.amazon.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/users/*")
public class Users extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
            System.out.println("get request recieved @ /users");

            if (req.getPathInfo() != null) {
                int getID = Integer.parseInt(req.getPathInfo().substring(1));
                if (req.getSession().getAttribute("role") != null) {
                    if (req.getSession().getAttribute("role").equals("Admin")) {
                        try {
                        User user = new UserService().findUser(getID);
                        resp.getWriter().append(objectMapper.writeValueAsString(user));
                        resp.setContentType("application/json");
                        resp.setStatus(201);
    
                        } catch (IOException e) {
                            resp.setStatus(400);
    
                            Logger logger = Logger.getLogger(Users.class);
                            logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                        
                        }
                    } else {
                        resp.setStatus(403);
                        resp.getWriter().append("Unauthorized");
    
                    }    
                } else {
                    resp.setStatus(403);
                    resp.getWriter().append("You are not logged in!");

                }   
            } else {
                if (req.getSession().getAttribute("role") != null) {
                    if (req.getSession().getAttribute("role").equals("Admin")) {
                        try {
                            ArrayList<User> users = new UserService().getAllUsers();
                            resp.getWriter().append(objectMapper.writeValueAsString(users));
                            resp.setContentType("application/json");
                            resp.setStatus(200);
    
                        } catch (Exception e) {
                            resp.setStatus(400);
                            e.printStackTrace();
    
                        }
                    } else {
                        resp.setStatus(403);
                        resp.getWriter().append("Unauthorized");
    
                    }
                } else {
                    resp.setStatus(403);
                    resp.getWriter().append("You are not logged in!");

                }
            }
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("post request recieved @ /users");
            JSONObject jsonObject = new JSONObject(IOUtils.toString(req.getReader()));

            try {
                ArrayList<User> users = new UserService().createUser(jsonObject);
                resp.getWriter().append(objectMapper.writeValueAsString(users));
                resp.setContentType("application/json");
                resp.setStatus(201);

            } catch (JSONException e) {
                // crash and burn
                throw new IOException("Error parsing JSON request string");
        
            }
        }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("put request recieved @ /users");
            JSONObject jsonObject = new JSONObject(IOUtils.toString(req.getReader()));
            int editID = Integer.parseInt(req.getPathInfo().substring(1));

            if (req.getSession().getAttribute("id") != null) {
                if (req.getSession().getAttribute("id").equals("editID")) {
                    try {
                        User user = new UserService().editUser(editID, jsonObject);
                        resp.getWriter().append(objectMapper.writeValueAsString(user));
                        resp.setContentType("application/json");
                        resp.setStatus(201);
    
                    } catch (JSONException e) {
                        // crash and burn
                        throw new IOException("Error parsing JSON request string");
    
                    }
                } else {
                    resp.setStatus(403);
                    resp.getWriter().append("Unauthorized");
    
                }
            } else {
                resp.setStatus(403);
                resp.getWriter().append("You are not logged in!");

            }
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("delete request recieved @ /users");
            int deleteID = Integer.parseInt(req.getPathInfo().substring(1));

            if (req.getSession().getAttribute("id") != null) {
                if (req.getSession().getAttribute("id").equals(deleteID)) {
                    try {
                        ArrayList<User> users = new UserService().deleteUser(deleteID);
                        resp.getWriter().append(objectMapper.writeValueAsString(users));
                        resp.setContentType("application/json");
                        resp.setStatus(201);
    
                    } catch (IOException e) {
                        resp.setStatus(400);
    
                        Logger logger = Logger.getLogger(Users.class);
                        logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                    
                    }    
                } else {
                    resp.setStatus(403);
                    resp.getWriter().append("Unauthorized");
    
                }
            } else {
                resp.setStatus(403);
                resp.getWriter().append("You are not logged in!");

            }
            
        }     
}