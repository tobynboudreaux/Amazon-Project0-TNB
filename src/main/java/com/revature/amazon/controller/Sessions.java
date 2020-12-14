package com.revature.amazon.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.HTTP;

import com.revature.amazon.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.amazon.model.User;

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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			JSONObject jsonObject = new JSONObject(IOUtils.toString(req.getReader()));

			User user = new SessionService().validateUser(jsonObject);
			HttpSession session = req.getSession();
			session.setAttribute("email", user.getEmail());
			session.setAttribute("role", user.getRoleName());
			session.setAttribute("id", user.getId());
			resp.getWriter().append(objectMapper.writeValueAsString(user));
			resp.setContentType("application/json");
			resp.setStatus(201);
			Logger logger = Logger.getLogger(Sessions.class);
			logger.info(user.getEmail() + " Logged in.");

	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("email") == null) {
			response.setStatus(400);
			response.getWriter().print("Error - not logged in yet");

		} else {
			Logger logger = Logger.getLogger(Sessions.class);
			logger.info(request.getSession().getAttribute("email") + " Logged out.");

			request.getSession().invalidate();
			response.getWriter().print("Successfully Logged Out!");

		}
	}
}