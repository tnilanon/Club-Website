package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.BookmarkDAO;
import edu.cmu.cs15437.clubwebsite.databeans.User;
import edu.cmu.cs15437.clubwebsite.databeans.Bookmark;
import edu.cmu.cs15437.clubwebsite.formbeans.AddBookmarkForm;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import java.util.*;

public class AddBookmarkAction extends Action {
	private FormBeanFactory< AddBookmarkForm > addBookmarkFormBeanFactory = FormBeanFactory.getInstance(AddBookmarkForm.class);
	
	private BookmarkDAO bookmarkDAO;
	
	public AddBookmarkAction(Model model) {
		bookmarkDAO = model.getBookmarkDAO();
	}
	
	public String getName() {
		return "addBookmark.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			AddBookmarkForm form = addBookmarkFormBeanFactory.create(request);
			request.setAttribute("form", form);
			
			// No form is passed in; silently ignore
			if (! form.isPresent()) {
				return "/myBookmarks.do";
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; let the user try again
			if (errors.size() > 0) {
				request.getSession().setAttribute("tempErrorList", errors);
				return "/myBookmarks.do";
			}
			
			// Add!
			Bookmark b = new Bookmark();
			b.setUserEmailAddress(((User) request.getSession().getAttribute("user")).getEmailAddress());
			b.setUrl(form.getUrl());
			b.setComment(form.getComment());
			b.setClick(0);
			bookmarkDAO.create(b);
			
			// Redirect to reflect newly added value
			return "/myBookmarks.do";
		} catch (DAOException e) {
			errors.add(e.getMessage());
			request.getSession().setAttribute("tempErrorList", errors);
			return "/myBookmarks.do";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			request.getSession().setAttribute("tempErrorList", errors);
			return "/myBookmarks.do";
		}
	}
}

