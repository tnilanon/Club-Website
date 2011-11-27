package edu.cmu.cs15437.clubwebsite.model;

import edu.cmu.cs15437.clubwebsite.databeans.VideoCommentBean;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.Transaction;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.MatchArg;

import java.util.*;

public class VideoCommentDAO {
	private BeanFactory< VideoCommentBean > videoCommentFactory;
	
	public VideoCommentDAO() throws DAOException {
		try {
			BeanTable< VideoCommentBean > videoCommentTable = BeanTable.getInstance(VideoCommentBean.class, "clubweb_video_comment");
			if(! videoCommentTable.exists()) videoCommentTable.create("commentId");
			videoCommentTable.setIdleConnectionCleanup(true);
			videoCommentFactory = videoCommentTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory< VideoCommentBean > getFactory() {
		return videoCommentFactory;
	}
	
	public VideoCommentBean lookupWithCommentId(int commentId) {
		try {
			return videoCommentFactory.lookup(commentId);
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			return null;
		}
	}
	
	public List< VideoCommentBean > getCommentsWithVideoId(int videoId) throws DAOException {
		try {
			return Arrays.asList(videoCommentFactory.match(MatchArg.equals("videoId", videoId)));
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			return null;
		}
	}
	
	public VideoCommentBean create(VideoCommentBean bean) throws DAOException {
		try {
			Transaction.begin();
			VideoCommentBean dbVideoComment = videoCommentFactory.create();
			videoCommentFactory.copyInto(bean, dbVideoComment);
			Transaction.commit();
			return dbVideoComment;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
			return null;
		}
	}
	
	public boolean destroy(int commentId) {
		try {
			Transaction.begin();
			VideoCommentBean dbVideoComment = videoCommentFactory.lookup(commentId);
			if (dbVideoComment == null) {
				throw new DAOException("VideoCommentBean " + String.valueOf(commentId) + " does not exist");
			}
			videoCommentFactory.delete(commentId);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
			return false;
		}
	}
}

