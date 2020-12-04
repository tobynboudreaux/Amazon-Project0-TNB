package com.revature.amazon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.amazon.service.SetupService;

@WebServlet("/")
public class Setup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
                System.out.println("GET request recieved @ /");
        
        try {
            String getIt = new SetupService().setupData();
            resp.getWriter().append(getIt);
            resp.setContentType("application/json");
            resp.setStatus(201);
        
        } catch (IOException e) {
            resp.setStatus(400);

            Logger logger = Logger.getLogger(Users.class);
            logger.debug(e.toString() + "QueryString: " + req.getQueryString());               
        
        }  
	}
}