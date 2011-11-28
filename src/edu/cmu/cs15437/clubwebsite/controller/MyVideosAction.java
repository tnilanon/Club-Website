package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.VideoDAO;
import edu.cmu.cs15437.clubwebsite.databeans.UserBean;

import org.mybeans.dao.DAOException;

import java.util.*;

public class MyVideosAction extends Action {
	private VideoDAO videoDAO;
	
	public MyVideosAction(Model model) {
		videoDAO = model.getVideoDAO();
	}
	
	public String getName() {
		return "myVideos.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List< String > errors = (List< String >) session.getAttribute("tempErrorList");
		session.removeAttribute("tempErrorList");
		if (errors == null) errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			request.setAttribute("listType", "myVideos");
			request.setAttribute("videoList", videoDAO.getUserVideos(user.getUserId()));
			
			return "videosMy.jsp";
		} catch(DAOException e) {
			errors.add(e.getMessage());
			return "videosMy.jsp";
		}
	}
}

