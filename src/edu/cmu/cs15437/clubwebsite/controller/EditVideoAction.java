package edu.cmu.cs15437.clubwebsite.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs15437.clubwebsite.databeans.UserBean;
import edu.cmu.cs15437.clubwebsite.databeans.VideoBean;
import edu.cmu.cs15437.clubwebsite.formbeans.EditVideoForm;
import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.VideoDAO;

public class EditVideoAction extends Action {
	private FormBeanFactory< EditVideoForm > editVideoFormBeanFactory = FormBeanFactory.getInstance(EditVideoForm.class);
	private VideoDAO videoDAO;
	
	public EditVideoAction(Model model) {
		videoDAO = model.getVideoDAO();
	}
	
	public String getName() {
		return "editVideo.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List< String > errors = (List< String >) session.getAttribute("tempErrorList");
		session.removeAttribute("tempErrorList");
		if (errors == null) errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			EditVideoForm form = editVideoFormBeanFactory.create(request);
			
			// No form is passed in; silently ignore
			if (! form.isPresent()) {
				return "editVideo.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				request.getSession().setAttribute("tempErrorList", errors);
				return "/editVideo.do";
			}
			
			int videoId = Integer.parseInt(form.getVideoId());
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			VideoBean video = videoDAO.lookupWithVideoId(videoId);
			
			int levelInt = -1;
			String levelStr = form.getRadio();
			if( levelStr.equals("1"))		levelInt = 1;
			else if( levelStr.equals("2"))	levelInt = 2;
			else if( levelStr.equals("3"))	levelInt = 3;
			else if( levelStr.equals("4"))	levelInt = 4;
			else if( levelStr.equals("5"))	levelInt = 5;
			else {
				errors.add("Invalid permission level");
				request.getSession().setAttribute("tempErrorList", errors);
				return "/editVideo.do";
			}
			
			// Only owner and admin can edit
			if( (user.getUserId() == video.getOwnerId()) || (user.getUserGroup() == 5) ) {
				videoDAO.updateAccessLevel(videoId, levelInt);
				videoDAO.updateDate(videoId, new Date());
				videoDAO.updateDescription(videoId, form.getDescription());
			}
			
			return "/myVideos.do";
		} catch(DAOException e) {
			errors.add(e.getMessage());
			request.getSession().setAttribute("tempErrorList", errors);
			return "/myVideos.do";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			request.getSession().setAttribute("tempErrorList", errors);
			return "/myVideos.do";
		}
	}

}

