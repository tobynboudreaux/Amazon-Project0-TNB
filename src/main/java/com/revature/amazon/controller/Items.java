package com.revature.amazon.controller;

import java.io.IOException;
import java.io.BufferedReader;
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

import com.revature.amazon.model.Item;
import com.revature.amazon.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/items/*")
public class Items extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
            System.out.println("get request recieved @ /items");

            if (req.getQueryString() != null) {
                String requestKey = req.getQueryString().split("=")[0];
                String requestValue = req.getQueryString().split("=")[1];

                try {
                    Item item = new ItemService(requestKey, requestValue).findItem();
                    resp.getWriter().append(objectMapper.writeValueAsString(item));
                    resp.setContentType("application/json");
                    resp.setStatus(201);

                } catch (IOException e) {
                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Items.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
               
                }     
            } else {
                try {
                    ArrayList<Item> items = new ItemService().getAllItems();
                    resp.getWriter().append(objectMapper.writeValueAsString(items));
                    resp.setContentType("application/json");
                    resp.setStatus(200);

                } catch (IOException e) {
                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Items.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString()); 
                
                }
            }
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("post request recieved @ /items");

            JSONObject jsonObject = new JSONObject(IOUtils.toString(req.getReader()));

            if (req.getSession() != null) {
                if (req.getSession().getAttribute("role").equals("Seller") || req.getSession().getAttribute("role").equals("Admin")) {
                    try {
                        ArrayList<Item> items = new ItemService().createItem(jsonObject);
                        resp.getWriter().append(objectMapper.writeValueAsString(items));
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("put request recieved @ /items");
            int editID = Integer.parseInt(req.getPathInfo().substring(1));
            JSONObject jsonObject = new JSONObject(IOUtils.toString(req.getReader()));
            
            if (req.getSession().getAttribute("role") != null) {
                if (req.getSession().getAttribute("role").equals("Seller") || req.getSession().getAttribute("role").equals("Admin")) {
                    try {
                    Item item = new ItemService().editItem(editID, jsonObject);
                    resp.getWriter().append(objectMapper.writeValueAsString(item));
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
            System.out.println("delete request recieved @ /items");
            int deleteID = Integer.parseInt(req.getPathInfo().substring(1));
            
            if (req.getSession().getAttribute("role") != null) {
                if (req.getSession().getAttribute("role").equals("Seller") || req.getSession().getAttribute("role").equals("Admin")) {
                    try {
                        ArrayList<Item> items = new ItemService().deleteItem(deleteID);
                        resp.getWriter().append(objectMapper.writeValueAsString(items));
                        resp.setContentType("application/json");
                        resp.setStatus(201);
    
                    } catch (IOException e) {
                        resp.setStatus(400);
    
                        Logger logger = Logger.getLogger(Items.class);
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