package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.databeans.User;

import java.io.IOException;

public class Controller extends HttpServlet {

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		
		Action.add(new RegisterAction(model));
		Action.add(new LoginAction(model));
		Action.add(new LogoutAction(model));
		
		Action.add(new AddBookmarkAction(model));
		Action.add(new ClickLinkAction(model));
		Action.add(new DeleteBookmarkAction(model));
		
		Action.add(new MyBookmarksAction(model));
		Action.add(new AllBookmarksAction(model));
		Action.add(new Top10BookmarksAction(model));
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = performAction(request);
		sendToNextPage(nextPage, request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String performAction(HttpServletRequest request) {
		User		user		= (User) request.getSession().getAttribute("user");
		String		action		= getActionNameFromPath(request.getServletPath());
		
		if (action.equals("register.do") || action.equals("login.do")) {
			// Allow these actions without logging in first
			return Action.perform(action, request);
		}
		
		if (user == null) {
			return Action.perform("login.do", request);
		}
		
		if (action.equals("home")) {
			return Action.perform("myBookmarks.do", request);
		}
		
		return Action.perform(action, request);
	}
	
	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		System.out.println(request.getSession().getAttribute("user"));
//		System.out.println("\t\t" + request.getRequestURI());
//		System.out.println();
		
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

