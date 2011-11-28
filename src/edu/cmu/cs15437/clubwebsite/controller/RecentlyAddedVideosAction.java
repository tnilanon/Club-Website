package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.VideoDAO;
import edu.cmu.cs15437.clubwebsite.databeans.UserBean;

import org.mybeans.dao.DAOException;

import java.util.*;

public class RecentlyAddedVideosAction extends Action {
	private VideoDAO videoDAO;
	private int videoCount = 10;  // Default number of shown videos
	
	public RecentlyAddedVideosAction(Model model) {
		videoDAO = model.getVideoDAO();
	}
	
	public String getName() {
		return "recentlyAddedVideos.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List< String > errors = (List< String >) session.getAttribute("tempErrorList");
		session.removeAttribute("tempErrorList");
		if (errors == null) errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			request.setAttribute("listType", "recentlyAddedVideos");
			request.setAttribute("videoList", videoDAO.getRecentlyAddedVideos(videoCount,user.getUserGroup()));
			
			return "videosRecentlyAdded.jsp";
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "videosRecentlyAdded.jsp";
		}
	}
}

