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
		return "/manageVideo.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			
			VideoForm form = videoFormBeanFactory.create(request);
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			VideoBean video = videoDAO.lookupWithVideoId(Integer.parseInt(form.getVideoID()));
			
			if( form.getButton().equals("deleteVideo") ){
				// Only owner and admin can delete
				if( (user.getUserId()==video.getOwnerId()) || (user.getUserGroup()==5) )
					videoDAO.destroy(Integer.parseInt(form.getVideoID()));
				return "/myVideo.do";
			}
			else if( form.getButton().equals("editVideo") ){
				return "/editVideo.do";
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

