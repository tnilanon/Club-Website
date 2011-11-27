package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.UserDAO;
import edu.cmu.cs15437.clubwebsite.databeans.UserBean;
import edu.cmu.cs15437.clubwebsite.formbeans.LoginForm;
import edu.cmu.cs15437.clubwebsite.formbeans.RegisterForm;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import java.util.*;

public class RegisterAction extends Action {
	private FormBeanFactory< LoginForm > loginFormBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	private FormBeanFactory< RegisterForm > registerFormBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	
	private UserDAO userDAO;
	
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public String getName() {
		return "register.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			RegisterForm form = registerFormBeanFactory.create(request);
			
			// No form is passed in; let the user try again
			if (! form.isPresent()) {
				return "register.jsp";
			}
			
			errors.addAll(loginFormBeanFactory.create(request).getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				return "login.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				return "register.jsp";
			}
			
			// Register the user
			UserBean user = new UserBean(-1);
			user.setUserName(form.getUserName());
			user.setEmailAddress(form.getEmailAddress());
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());
			user.setSex(form.getSex());
			user.setUserGroup(0);
			user.setMembershipExpirationDate(Date());
			user.setPassword(form.getPassword());
			userDAO.create(user);
			
			// Remeber the user
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			// It's time to redirect
			String redirectTo = (String) session.getAttribute("redirectTo");
			session.removeAttribute("redirectTo");
			if (redirectTo != null) return redirectTo;
			return "profile.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "register.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "register.jsp";
		}
	}
}

