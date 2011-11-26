package edu.cmu.cs15437.clubwebsite.model;

import edu.cmu.cs15437.clubwebsite.databeans.Bookmark;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.Transaction;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.MatchArg;

import java.util.*;

public class BookmarkDAO {
	private BeanFactory< Bookmark > factory;
	
	public BookmarkDAO() throws DAOException {
		try {
			BeanTable< Bookmark > table = BeanTable.getInstance(Bookmark.class, "tnilanon_clubwebsite_bookmark");
			if(! table.exists()) table.create("id");
			table.setIdleConnectionCleanup(true);
			factory = table.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory< Bookmark > getFactory() {
		return factory;
	}
	
	public Bookmark lookup(int id) throws DAOException {
		try {
			return factory.lookup(id);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Bookmark[] list(String userEmailAddress) throws DAOException {
		try {
			Bookmark[] bookmarks = factory.match(MatchArg.equals("userEmailAddress", userEmailAddress));
			Arrays.sort(bookmarks); // Sort by popularity
			return bookmarks;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Bookmark[] listAll() throws DAOException {
		try {
			Bookmark[] bookmarks = factory.match();
			Arrays.sort(bookmarks); // Sort by popularity
			return bookmarks;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public void create(Bookmark bookmark) throws DAOException {
		try {
			Transaction.begin();
			Bookmark dbBookmark = factory.create();
			factory.copyInto(bookmark, dbBookmark);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void delete(int id, String userEmailAddress) throws DAOException {
		try {
			Transaction.begin();
			Bookmark b = factory.lookup(id);
			
			if (b == null) {
				throw new DAOException("Bookmark does not exist");
			}
			if (! b.getUserEmailAddress().equals(userEmailAddress)) {
				throw new DAOException("Bookmark is not owned by the user");
			}
			
			factory.delete(id);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void incrementClick(int id, String userEmailAddress) throws DAOException {
		try {
			Transaction.begin();
			Bookmark b = factory.lookup(id);
			
			if (b == null) {
				throw new DAOException("Bookmark does not exist");
			}
			
			if (b.getUserEmailAddress().equals(userEmailAddress)) {
				// Bookmark is owned by the user
			} else {
				// Bookmark is not owned by the user
				b.setClick(b.getClick() + 1);
			}
			
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}

