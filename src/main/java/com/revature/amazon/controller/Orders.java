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
            if (req.getSession().getAttribute("role") != null) {
                if (req.getPathInfo() != null) {
                    int getID = Integer.parseInt(req.getPathInfo().substring(1));

                    try {
                        Order order = new OrderService().findOrder(getID);
                        if (req.getSession().getAttribute("id").equals(order.getBuyerID())) {
                            resp.getWriter().append(objectMapper.writeValueAsString(order));
                            resp.setContentType("application/json");
                            resp.setStatus(201);
                        } else {
                            resp.setStatus(403);
                            resp.getWriter().append("Unauthorized");
    
                        } 
                    } catch (IOException e) {
                        resp.setStatus(400);

                        Logger logger = Logger.getLogger(Users.class);
                        logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
                    
                    }    
                } else {

                    try {
                        if (req.getSession().getAttribute("role").equals("Admin")) {
                            ArrayList<Order> orders = new OrderService().getAllOrders();
                            resp.getWriter().append(objectMapper.writeValueAsString(orders));
                            resp.setContentType("application/json");
                            resp.setStatus(200);
                        } else {
                            resp.setStatus(403);
                            resp.getWriter().append("Unauthorized");
    
                        } 
                    } catch (Exception e) {

                        resp.setStatus(400);
                        e.printStackTrace();
                    
                    }
                }
            } else {
                resp.setStatus(403);
                resp.getWriter().append("You are not logged in!");

            }  
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("post request recieved @ /orders");
            if (req.getSession().getAttribute("role") != null) {
                if (req.getPathInfo() != null) {
                    String[] pathInfo = req.getPathInfo().substring(1).split("/");
                    String type = pathInfo[0];
                    int id = Integer.parseInt(pathInfo[1]);
                    Order order = new Order();
                    switch (type){
                        case "ship":
                            order = new OrderService().shipOrder(id);
                            if (req.getSession().getAttribute("id").equals(order.getBuyerID())) {
                                resp.getWriter().append(objectMapper.writeValueAsString(order));
                                resp.setContentType("application/json");
                                resp.setStatus(200);
                            } else {
                                resp.setStatus(403);
                                resp.getWriter().append("Unauthorized");
        
                            } 
                            break;
                        case "recall":
                            order = new OrderService().recallOrder(id);
                            if (req.getSession().getAttribute("id").equals(order.getBuyerID())) {
                                resp.getWriter().append(objectMapper.writeValueAsString(order));
                                resp.setContentType("application/json");
                                resp.setStatus(200);
                            } else {
                                resp.setStatus(403);
                                resp.getWriter().append("Unauthorized");
                                
                            } 
                            break;
                        case "create":
                            order = new OrderService().createOrder(id);
                            resp.getWriter().append(objectMapper.writeValueAsString(order));
                            resp.setContentType("application/json");
                            resp.setStatus(200);
                            break;
                        default:
                            resp.getWriter().append("Incorrect modifier detected. Please use either ship, recall, or create followed by /the id of the order you want to post to");
                    }
                }
                else {
                    resp.getWriter().append("Please provide more information in the path");
                }
            } else {
                resp.setStatus(403);
                resp.getWriter().append("You are not logged in!");

            }  
        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("put request recieved @ /orders");

            if (req.getSession().getAttribute("role") != null) {
                if (req.getPathInfo() != null) {
                    String[] pathInfo = req.getPathInfo().substring(1).split("/");
                    String type = pathInfo[0];
                    int orderId = Integer.parseInt(pathInfo[1]);
                    int itemId = Integer.parseInt(pathInfo[2]);
                    Order order = new Order();
                    if (type.equals("add"))
                    {
                        order = new OrderService().addItemToOrder(orderId, itemId);
                        if (req.getSession().getAttribute("id").equals(order.getBuyerID())) {
                            resp.getWriter().append(objectMapper.writeValueAsString(order));
                            resp.setContentType("application/json");
                            resp.setStatus(200);
                        } else {
                            resp.setStatus(403);
                            resp.getWriter().append("Unauthorized");
        
                        } 
                    } else if (type.equals("remove")) {
                        order = new OrderService().removeItemFromOrder(orderId, itemId);
                        if (req.getSession().getAttribute("id").equals(order.getBuyerID())) {
                            resp.getWriter().append(objectMapper.writeValueAsString(order));
                            resp.setContentType("application/json");
                            resp.setStatus(200);
                        } else {
                            resp.setStatus(403);
                            resp.getWriter().append("Unauthorized");
        
                        } 

                    } else {
                        resp.getWriter().append("Incorrect modifier detected. Please use either add or remove followed by /the id of the item you want to add to your order");
                        resp.setStatus(400);
                    }
                } else {
                    resp.getWriter().append("Please provide more information in the path");
                    resp.setStatus(404);
                }    
            } else {
                resp.setStatus(403);
                resp.getWriter().append("You are not logged in!");

            }  
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            System.out.println("delete request recieved @ /orders");
            int orderId = Integer.parseInt(req.getPathInfo().substring(1));
            Order order = new OrderService().findOrder(orderId);

            if (req.getSession().getAttribute("role") != null) {
                if (req.getSession().getAttribute("id").equals(order.getBuyerID())) {
                    if (req.getPathInfo() != null) {
                        String delete = new OrderService().deleteOrder(orderId);
                        resp.getWriter().append(delete);
                        resp.setStatus(200);

                    } else {
                        resp.getWriter().append("Please provide more information in the path");
                        resp.setStatus(404);
                    }
                } else {
                    resp.setStatus(403);
                    resp.getWriter().append("Unauthorized");

                }    
            } else {
                resp.setStatus(403);
                resp.getWriter().append("You are not logged in!");

            }  

            // String delete = new OrderService().deleteOrder(orderID);
        }
}