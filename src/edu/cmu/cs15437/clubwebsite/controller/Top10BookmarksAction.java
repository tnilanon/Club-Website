package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.BookmarkDAO;
import edu.cmu.cs15437.clubwebsite.databeans.Bookmark;

import org.mybeans.dao.DAOException;

import java.util.*;

public class Top10BookmarksAction extends Action {
	private BookmarkDAO bookmarkDAO;
	
	public Top10BookmarksAction(Model model) {
		bookmarkDAO = model.getBookmarkDAO();
	}
	
	public String getName() {
		return "top10Bookmarks.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			request.setAttribute("listType", "top10Bookmarks");
			Bookmark[] a = bookmarkDAO.listAll();
			request.setAttribute("bookmarkList", Arrays.copyOf(a, Math.min(10, a.length)));
			
			return "listBookmarks.jsp";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			return "listBookmarks.jsp";
		}
	}
}

