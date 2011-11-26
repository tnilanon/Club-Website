package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;

public class LogoutAction extends Action {
	public LogoutAction(Model model) {
	}
	
	public String getName() {
		return "logout.do";
	}
	
	public String perform(HttpServletRequest request) {
		request.getSession(false).removeAttribute("user");
		
		return "login.jsp";
	}	
}

