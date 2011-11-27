package edu.cmu.cs15437.clubwebsite.model;

import edu.cmu.cs15437.clubwebsite.databeans.VideoCategoryBean;
import edu.cmu.cs15437.clubwebsite.databeans.VideoTagBean;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.Transaction;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.MatchArg;

import java.util.*;

public class VideoTagDAO {
	private BeanFactory< VideoTagBean > videoTagFactory;
	private VideoCategoryDAO videoCategoryDAO;
	
	public VideoTagDAO(VideoCategoryDAO d) throws DAOException {
		try {
			BeanTable< VideoTagBean > videoTagTable = BeanTable.getInstance(VideoTagBean.class, "clubweb_video_tag");
			if(! videoTagTable.exists()) videoTagTable.create("tagId");
			videoTagTable.setIdleConnectionCleanup(true);
			videoTagFactory = videoTagTable.getFactory();
			
			videoCategoryDAO = d;
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory< VideoTagBean > getFactory() {
		return videoTagFactory;
	}
	
	public VideoTagBean lookupWithTagId(int tagId) throws DAOException {
		try {
			return videoTagFactory.lookup(tagId);
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			return null;
		}
	}
	
	public VideoTagBean create(VideoTagBean bean) throws DAOException {
		try {
			Transaction.begin();
			VideoTagBean dbVideoTag = videoTagFactory.create();
			videoTagFactory.copyInto(bean, dbVideoTag);
			Transaction.commit();
			return dbVideoTag;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
			return null;
		}
	}
	
	public boolean destroy(int tagId) {
		try {
			Transaction.begin();
			VideoTagBean dbVideoTag = videoTagFactory.lookup(tagId);
			if (dbVideoTag == null) {
				throw new DAOException("VideoTagBean " + String.valueOf(tagId) + " does not exist");
			}
			videoTagFactory.delete(tagId);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
			return false;
		}
	}
	
	public String descriptionForTagId(int tagId) {
		return videoCategoryDAO.lookupWithCategoryId(lookupWithTagId(tagId).getCategoryId()).getDescription();
	}
	
	public List< Integer > videoIdsMatchingCategories(List< VideoCategoryBean > categories) {
		MatchArg m = parseCategoriesIntoMatchArg(categories);
		List< VideoTagBean > videoTags = videoTagFactory.match(m);
			List< Integer > videoIds = new ArrayList< Integer >(videoTags.size());
			for (VideoTagBean videoTag : videoTags) {
				videoIds.add(videoTag.getVideoId());
			}
	}
	
	private MatchArg parseCategoriesIntoMatchArg(List< VideoCategoryBean > categories) {
		MatchArg m = null;
		for (VideoCategoryBean category : categories) {
			if (m == null)
				m = MatchArg.equals("categoryId", category.getCategoryId());
			else
				m = MatchArg.and(m, MatchArg.equals("categoryId", category.getCategoryId()));
		}
		return m;
	}
}

