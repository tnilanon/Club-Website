package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.BookmarkDAO;
import edu.cmu.cs15437.clubwebsite.databeans.User;
import edu.cmu.cs15437.clubwebsite.formbeans.DeleteBookmarkForm;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import java.util.*;

public class DeleteBookmarkAction extends Action {
	private FormBeanFactory< DeleteBookmarkForm > deleteBookmarkFormBeanFactory = FormBeanFactory.getInstance(DeleteBookmarkForm.class);
	
	private BookmarkDAO bookmarkDAO;
	
	public DeleteBookmarkAction(Model model) {
		bookmarkDAO = model.getBookmarkDAO();
	}
	
	public String getName() {
		return "deleteBookmark.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		try {
			DeleteBookmarkForm form = deleteBookmarkFormBeanFactory.create(request);
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
			
			// Delete
			bookmarkDAO.delete(Integer.parseInt(form.getBookmarkIdStr()), ((User) request.getSession().getAttribute("user")).getEmailAddress());
			
			// Report error in deleting
			if (errors.size() > 0) {
				request.getSession().setAttribute("tempErrorList", errors);
				return "/myBookmarks.do";
			}
			
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

