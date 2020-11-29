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

import com.revature.amazon.model.Item;
import com.revature.amazon.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/items")
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

            if (req.getQueryString() != null) {

                String requestKey = req.getQueryString().split("=")[0];
                String requestValue = req.getQueryString().split("=")[1];
                                
                try {
                    
                    Item item = new ItemService(requestKey, requestValue).createItem();
                    resp.getWriter().append(objectMapper.writeValueAsString(item));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                } catch (IOException e) {

                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Items.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                }
                    
            } else {
                resp.setStatus(400);

                System.out.println("Item not found");
            }
        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("put request recieved @ /items");
           
            if (req.getQueryString() != null) {

                String requestid = req.getQueryString().split("&")[0];
                String requestbalance = req.getQueryString().split("&")[1];
                String requestKey = requestid.split("=")[1];
                String requestValue = requestbalance.split("=")[1];
                
                try {

                    Item item = new ItemService(requestKey, requestValue).updateItemPrice();
                    resp.getWriter().append(objectMapper.writeValueAsString(item));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                } catch (IOException e) {

                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Items.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                }
                    
            } else {
                resp.setStatus(400);

                System.out.println("Item not found");
            }
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("delete request recieved @ /items");
            
            if (req.getQueryString() != null) {

                String requestKey = req.getQueryString().split("=")[0];
                String requestValue = req.getQueryString().split("=")[1];
                
                try {

                    ArrayList<Item> items = new ItemService(requestKey, requestValue).deleteItem();
                    resp.getWriter().append(objectMapper.writeValueAsString(items));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                } catch (IOException e) {

                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Items.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                }
                    
            } else {
                resp.setStatus(400);

                System.out.println("Item not found");
            }
        }
}