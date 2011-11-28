package edu.cmu.cs15437.clubwebsite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs15437.clubwebsite.databeans.UserBean;
import edu.cmu.cs15437.clubwebsite.formbeans.ChangePasswordForm;
import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.UserDAO;

public class ChangePasswordAction extends Action {
	private FormBeanFactory< ChangePasswordForm > changePasswordFormBeanFactory = FormBeanFactory.getInstance(ChangePasswordForm.class);
	
	private UserDAO userDAO;
	
	public ChangePasswordAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public String getName() {
		return "changePassword.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			ChangePasswordForm form = changePasswordFormBeanFactory.create(request);
			
			// No form is passed in; let the user try again
			if (! form.isPresent()) {
				return "changePassword.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				return "changePassword.jsp";
			}

			
			// Update Password 
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user");
			userDAO.updatePassword(user.getUserId(), form.getPassword());
			user = userDAO.lookupWithUserId(user.getUserId());
			
			// Remember the user
			session.setAttribute("user", user);
			
			return "profile.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "changePassword.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "changePassword.jsp";
		}
	}
}

