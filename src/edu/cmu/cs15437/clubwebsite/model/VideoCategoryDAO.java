package edu.cmu.cs15437.clubwebsite.model;

import edu.cmu.cs15437.clubwebsite.databeans.VideoCategoryBean;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.Transaction;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.MatchArg;

import java.util.*;

public class VideoCategoryDAO {
	private BeanFactory< VideoCategoryBean > videoCategoryFactory;
	
	public VideoCategoryDAO() throws DAOException {
		try {
			BeanTable< VideoCategoryBean > videoCategoryTable = BeanTable.getInstance(VideoCategoryBean.class, "clubweb_video_category");
			if(! videoCategoryTable.exists()) videoCategoryTable.create("categoryId");
			videoCategoryTable.setIdleConnectionCleanup(true);
			videoCategoryFactory = videoCategoryTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory< VideoCategoryBean > getFactory() {
		return videoCategoryFactory;
	}
	
	public VideoCategoryBean lookupWithCategoryId(int categoryId) throws DAOException {
		try {
			return videoCategoryFactory.lookup(categoryId);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public VideoCategoryBean create(VideoCategoryBean bean) throws DAOException {
		try {
			Transaction.begin();
			VideoCategoryBean dbVideoCategory = videoCategoryFactory.create();
			videoCategoryFactory.copyInto(bean, dbVideoCategory);
			Transaction.commit();
			return dbVideoCategory;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean destroy(int categoryId) throws DAOException {
		try {
			Transaction.begin();
			VideoCategoryBean dbVideoCategory = videoCategoryFactory.lookup(categoryId);
			if (dbVideoCategory == null) {
				throw new DAOException("VideoCategoryBean " + String.valueOf(categoryId) + " does not exist");
			}
			videoCategoryFactory.delete(categoryId);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}

