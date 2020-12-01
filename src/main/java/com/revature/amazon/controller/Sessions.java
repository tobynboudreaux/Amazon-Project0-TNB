package com.revature.amazon.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.amazon.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/sessions")
public class Sessions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sessions() {
		super();

	}

	ObjectMapper objectMapper = new ObjectMapper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader requestBody = request.getReader();
		StringBuilder requestBodyString = new StringBuilder();
		String requestBodyLine;
		String jsonRequestBody;

		while ((requestBodyLine = requestBody.readLine()) != null) {
			requestBodyString.append(requestBodyLine);

		}

		jsonRequestBody = requestBodyString.toString();
		SessionService userSession = objectMapper.readValue(jsonRequestBody, SessionService.class);

		if (userSession.validateUser()) {
			HttpSession session = request.getSession();
			session.setAttribute("email", userSession.getEmail());
			response.setContentType("text/html");
			PrintWriter pwriter = response.getWriter();
			pwriter.print("<h2>Welcome " + userSession.getEmail() + "!</h2>");
			pwriter.close();
			Logger logger = Logger.getLogger(Sessions.class);
			logger.info(userSession.getEmail() + " Logged in.");

		} else {
			Logger logger = Logger.getLogger(Sessions.class);
			logger.debug("Bad email or password: " + userSession.getEmail());
			response.setStatus(401);

		}

	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("email") == null) {
			response.setStatus(400);
			response.getWriter().print("Error - not logged in yet");

		} else {
			Logger logger = Logger.getLogger(Sessions.class);
			logger.info(request.getSession().getAttribute("email") + " Logged out.");

			request.logout();
			response.getWriter().print("Successfully Logged Out!");

		}
	}
}