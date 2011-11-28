package edu.cmu.cs15437.clubwebsite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs15437.clubwebsite.databeans.UserBean;
import edu.cmu.cs15437.clubwebsite.formbeans.AdmitMemberForm;
import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.UserDAO;

public class AdmitMemberAction extends Action {
	private FormBeanFactory< AdmitMemberForm > admitMemberFormBeanFactory = FormBeanFactory.getInstance(AdmitMemberForm.class);
	private UserDAO userDAO;
	
	public AdmitMemberAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public String getName() {
		return "admit.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			
			// Check if Admin
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			if( user.getUserGroup() != 5)
				return "login.jsp";
			
			request.setAttribute("listType", "admit");
			request.setAttribute("newUserList", userDAO.getPendingMemberShip());
			
			AdmitMemberForm form = admitMemberFormBeanFactory.create(request);
			
			// No form is passed in; let the user try again
			if (! form.isPresent()) {
				return "admitMember.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				return "admitMember.jsp";
			}
			
			// Update User Info
			int userId = userDAO.lookupWithEmailAddress(form.getEmailAddress()).getUserId();
			userDAO.updateUserGroup(userId, Integer.parseInt(form.getMemberType()));
			
			
			return "/admit.do";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "admitMember.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "admitMember.jsp";
		}
	}
}

