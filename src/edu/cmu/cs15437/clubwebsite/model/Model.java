package edu.cmu.cs15437.clubwebsite.model;

import javax.servlet.ServletException;
import javax.servlet.ServletConfig;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;

import java.io.File;

public class Model {
	private UserDAO userDAO;
	private BookmarkDAO bookmarkDAO;
	
	public Model(ServletConfig config) throws ServletException {
		try {
			String csvDirStr = config.getInitParameter("csvDir");
			if (csvDirStr != null && csvDirStr.length() > 0) {
				// Use CSV files
				File csvDir = new File(csvDirStr);
				BeanTable.useCSVFiles(csvDir);
			} else {
				String jdbcDriver	= config.getInitParameter("jdbcDriver");
				String jdbcURL		= config.getInitParameter("jdbcURL");
				String jdbcUser		= config.getInitParameter("jdbcUser");
				String jdbcPassword	= config.getInitParameter("jdbcPassword");
				BeanTable.useJDBC(jdbcDriver, jdbcURL, jdbcUser, jdbcPassword);
			}
			
			userDAO = new UserDAO();
			bookmarkDAO = new BookmarkDAO();
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public BookmarkDAO getBookmarkDAO() {
		return bookmarkDAO;
	}
}

