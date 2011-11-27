package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.databeans.UserBean;

import java.io.IOException;

public class Controller extends HttpServlet {

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		
		Action.add(new RegisterAction(model));
		Action.add(new LoginAction(model));
		Action.add(new LogoutAction(model));
		
		Action.add(new ProfileAction(model));
		Action.add(new AddVideoAction(model));
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = performAction(request);
		sendToNextPage(nextPage, request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String performAction(HttpServletRequest request) {
		UserBean	user		= (UserBean) request.getSession().getAttribute("user");
		String		action		= getActionNameFromPath(request.getServletPath());
		
		if (action.equals("home")) {
			return "home.jsp";
		}
		
		if (action.equals("login.do") || action.equals("register.do")) {
			// Allow these actions without logging in first
			return Action.perform(action, request);
		}
		
		if (user == null) {
			// User must login before performing privileged actions
			return Action.perform("login.do", request);
		}
		
		return Action.perform(action, request);
	}
	
	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (nextPage == null) {
			// Send back 404
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}
		
		if (nextPage.charAt(0) == '/') {
			String host = request.getServerName();
			String port = ":" + String.valueOf(request.getServerPort());
			if (port.equals(":80")) port = ""; // Cosmetic
			String contextPath = request.getContextPath();
			
			response.sendRedirect("http://" + host + port + contextPath + nextPage);
			return;
		}
		
		if (nextPage.charAt(0) == '*') {
			response.sendRedirect(nextPage.substring(1));
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/" + nextPage);
		dispatcher.forward(request, response);
	}
	
	protected static String getActionNameFromPath(String path) {
		// We are guaranteed that the path will begin with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}

