package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs15437.clubwebsite.model.Model;
import edu.cmu.cs15437.clubwebsite.model.BookmarkDAO;
import edu.cmu.cs15437.clubwebsite.databeans.User;
import edu.cmu.cs15437.clubwebsite.formbeans.ClickLinkForm;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import java.util.*;

public class ClickLinkAction extends Action {
	private FormBeanFactory< ClickLinkForm > clickLinkFormBeanFactory = FormBeanFactory.getInstance(ClickLinkForm.class);
	
	private BookmarkDAO bookmarkDAO;
	
	public ClickLinkAction(Model model) {
		bookmarkDAO = model.getBookmarkDAO();
	}
	
	public String getName() {
		return "clickLink.do";
	}
	
	public String perform(HttpServletRequest request) {
		List< String > errors = new ArrayList< String >();
		request.setAttribute("errors", errors);
		
		ClickLinkForm form = null;
		
		try {
			form = clickLinkFormBeanFactory.create(request);
			request.setAttribute("form", form);
			
			// No form is passed; complain
			if (! form.isPresent()) {
				errors.add("Please try again; it seems the webpage you received has been tampered");
			}
			
			errors.addAll(form.getValidationErrors());
			// If there is any error; send back to MyBookmarksAction
			if (errors.size() > 0) {
				request.getSession().setAttribute("tempErrorList", errors);
				return ("allBookmarks".equals(form.getListType()))? "/allBookmarks.do": ("top10Bookmarks".equals(form.getListType()))? "/top10Bookmarks.do": "/myBookmarks.do";
			}
			
			int bookmarkId = Integer.parseInt(form.getBookmarkIdStr());
			
			// Increment click count
			bookmarkDAO.incrementClick(bookmarkId, ((User) request.getSession().getAttribute("user")).getEmailAddress());
			
			// Redirect to the website
			// Fix it for the user who didn't type in http://
			String url = bookmarkDAO.lookup(bookmarkId).getUrl();
			return "*" + ((url.indexOf("http") != 0)? "http://": "")+ url;
		} catch (DAOException e) {
			errors.add(e.getMessage());
			request.getSession().setAttribute("tempErrorList", errors);
			return (form == null)? "/myBookmarks.do": ("allBookmarks".equals(form.getListType()))? "/allBookmarks.do": ("top10Bookmarks".equals(form.getListType()))? "/top10Bookmarks.do": "/myBookmarks.do";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			request.getSession().setAttribute("tempErrorList", errors);
			return (form == null)? "/myBookmarks.do": ("allBookmarks".equals(form.getListType()))? "/allBookmarks.do": ("top10Bookmarks".equals(form.getListType()))? "/top10Bookmarks.do": "/myBookmarks.do";
		}
	}
}

