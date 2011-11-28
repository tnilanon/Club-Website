package edu.cmu.cs15437.clubwebsite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs15437.clubwebsite.databeans.UserBean;
import edu.cmu.cs15437.clubwebsite.databeans.VideoBean;
import edu.cmu.cs15437.clubwebsite.formbeans.VideoForm;
import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.VideoDAO;

public class ManageVideoAction extends Action {
	private FormBeanFactory< VideoForm > videoFormBeanFactory = FormBeanFactory.getInstance(VideoForm.class);
	private VideoDAO videoDAO;
	
	public ManageVideoAction(Model model) {
		videoDAO = model.getVideoDAO();
	}
	
	public String getName() {
		return "manageVideo.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			VideoForm form = videoFormBeanFactory.create(request);
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				request.getSession().setAttribute("tempErrorList", errors);
				return "/myVideos.do";
			}
			
			int videoId = Integer.parseInt(form.getVideoId());
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			VideoBean video = videoDAO.lookupWithVideoId(videoId);
			
			// Only owner and admin can delete / edit
			if( (user.getUserId() == video.getOwnerId()) || (user.getUserGroup() == 5) ) {
				if ( "Delete Video".equals(form.getButton()) ) {
					videoDAO.destroy(videoId);
					return "/myVideos.do";
				} else if ( "Edit Info".equals(form.getButton()) ) {
					return "/editVideo.do";
				}
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

