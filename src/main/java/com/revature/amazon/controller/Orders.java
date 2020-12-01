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

import com.revature.amazon.model.Order;
import com.revature.amazon.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/orders/*")
public class Orders extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
            System.out.println("get request recieved @ /orders");

            if (req.getPathInfo() != null) {
                int getID = Integer.parseInt(req.getPathInfo().substring(1));

                try {
                    Order order = new OrderService().findOrder(getID);
                    resp.getWriter().append(objectMapper.writeValueAsString(order));
                    resp.setContentType("application/json");
                    resp.setStatus(201);
                
                } catch (IOException e) {
                    resp.setStatus(400);

                    Logger logger = Logger.getLogger(Users.class);
                    logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                
                }    
            } else {

                try {
                    ArrayList<Order> orders = new OrderService().getAllOrders();
                    resp.getWriter().append(objectMapper.writeValueAsString(orders));
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
            System.out.println("post request recieved @ /orders");

        }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("put request recieved @ /orders");
        
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("delete request recieved @ /orders");
 
        }
}