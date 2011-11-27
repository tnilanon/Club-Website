package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.UserDAO;
import edu.cmu.cs15437.clubwebsite.databeans.UserBean;
import edu.cmu.cs15437.clubwebsite.formbeans.LoginForm;
import edu.cmu.cs15437.clubwebsite.controller.Controller;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import java.util.*;

public class LoginAction extends Action {
	private FormBeanFactory< LoginForm > loginFormBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private UserDAO userDAO;
	
	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public String getName() {
		return "login.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			LoginForm form = loginFormBeanFactory.create(request);
			
			// Support redirect
			// Redirect to the original page if the servlet redirects the user here
			String actionName = Controller.getActionNameFromPath(request.getServletPath());
			if (! (actionName.equals("login.do") || actionName.equals(""))) {
				String queryString = request.getQueryString();
				String redirectTo = request.getServletPath() + "?" + ((queryString == null)? "": queryString);
				request.getSession().setAttribute("redirectTo", redirectTo);
			}
			
			// No form is passed in; let the user try again
			if (! form.isPresent()) {
				return "login.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				return "login.jsp";
			}
						
			// Look up the user
			UserBean user = userDAO.lookupWithEmailAddress(form.getEmailAddress());
			
			if (form.getButton().equals("Register")) {
				// User wants to register
				if (user != null) {
					errors.add("User already exists");
					return "login.jsp";
				}
				return "register.jsp";
			}
			
			if (user == null) {
				errors.add("User not found");
				return "login.jsp";
			}
			
			if (! user.checkPassword(form.getPassword())) {
				errors.add("Incorrect password");
				return "login.jsp";
			}
			
			// Remember the user
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			// It's time to redirect
			String redirectTo = (String) session.getAttribute("redirectTo");
			session.removeAttribute("redirectTo");
			if (redirectTo != null) return redirectTo;
			return "home.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "login.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "login.jsp";
		}
	}
}

