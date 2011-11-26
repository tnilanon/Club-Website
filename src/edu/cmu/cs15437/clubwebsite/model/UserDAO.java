package edu.cmu.cs15437.clubwebsite.model;

import edu.cmu.cs15437.clubwebsite.databeans.User;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.Transaction;
import org.mybeans.factory.RollbackException;

import java.util.*;

public class UserDAO {
	private BeanFactory< User > factory;
	
	public UserDAO() throws DAOException {
		try {
			BeanTable< User > table = BeanTable.getInstance(User.class, "tnilanon_clubwebsite_user");
			if(! table.exists()) table.create("emailAddress");
			table.setIdleConnectionCleanup(true);
			factory = table.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory< User > getFactory() {
		return factory;
	}
	
	public User lookup(String emailAddress) throws DAOException {
		try {
			return factory.lookup(emailAddress);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public User[] getUsers() throws DAOException {
		try {
			User[] users = factory.match();
			Arrays.sort(users); // Sort using User's compareTo()
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public void create(User user) throws DAOException {
		try {
			Transaction.begin();
			User dbUser = factory.create(user.getEmailAddress());
			factory.copyInto(user, dbUser);
			Transaction.commit();
		} catch (DuplicateKeyException e) {
			throw new DAOException("A user " + user.getEmailAddress() + " already exists");
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void setPassword(String emailAddress, String password) throws DAOException {
		try {
			Transaction.begin();
			User dbUser = factory.lookup(emailAddress);
			if (dbUser == null) {
				throw new DAOException("User " + emailAddress + " does not exist");
			}
			dbUser.setPassword(password);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}

