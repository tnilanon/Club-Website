package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.BookmarkDAO;
import edu.cmu.cs15437.clubwebsite.databeans.User;

import org.mybeans.dao.DAOException;

import java.util.*;

public class MyBookmarksAction extends Action {
	private BookmarkDAO bookmarkDAO;
	
	public MyBookmarksAction(Model model) {
		bookmarkDAO = model.getBookmarkDAO();
	}
	
	public String getName() {
		return "myBookmarks.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List< String > errors = (List< String >) session.getAttribute("tempErrorList");
		session.removeAttribute("tempErrorList");
		if (errors == null) errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("listType", "myBookmarks");
			request.setAttribute("bookmarkList", bookmarkDAO.list(user.getEmailAddress()));
			
			return "listBookmarks.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "listBookmarks.jsp";
		}
	}
}

