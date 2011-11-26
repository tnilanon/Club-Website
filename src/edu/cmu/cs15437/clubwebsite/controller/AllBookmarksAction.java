package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.BookmarkDAO;

import org.mybeans.dao.DAOException;

import java.util.*;

public class AllBookmarksAction extends Action {
	private BookmarkDAO bookmarkDAO;
	
	public AllBookmarksAction(Model model) {
		bookmarkDAO = model.getBookmarkDAO();
	}
	
	public String getName() {
		return "allBookmarks.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			request.setAttribute("listType", "allBookmarks");
			request.setAttribute("bookmarkList", bookmarkDAO.listAll());
			
			return "listBookmarks.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "listBookmarks.jsp";
		}
	}
}

