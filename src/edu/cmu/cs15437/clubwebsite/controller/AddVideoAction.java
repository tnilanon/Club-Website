package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.VideoDAO;
import edu.cmu.cs15437.clubwebsite.databeans.UserBean;
import edu.cmu.cs15437.clubwebsite.databeans.VideoBean;
import edu.cmu.cs15437.clubwebsite.formbeans.AddVideoForm;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import java.util.*;

public class AddVideoAction extends Action {
	private FormBeanFactory< AddVideoForm > addVideoFormBeanFactory = FormBeanFactory.getInstance(AddVideoForm.class);
	
	private VideoDAO videoDAO;
	
	public AddVideoAction(Model model) {
		videoDAO = model.getVideoDAO();
	}
	
	public String getName() {
		return "addVideo.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			AddVideoForm form = addVideoFormBeanFactory.create(request);
			
			// No form is passed in; silently ignore
			if (! form.isPresent()) {
				return "profile.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				return "profile.jsp";
			}
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			
			// Add~
			VideoBean bean = new VideoBean(-1);
			bean.setOwnerId(user.getUserId());
			bean.setAccessLevel(user.getUserGroup());
			bean.setLink(form.getLink());
			bean.setDescription(form.getDescription());
			bean.setDateValue(new Date().getTime());
			videoDAO.create(bean);
			
			return "/profile.do";
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "profile.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "profile.jsp";
		}
	}
}

