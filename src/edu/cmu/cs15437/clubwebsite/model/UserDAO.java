package edu.cmu.cs15437.clubwebsite.model;

import edu.cmu.cs15437.clubwebsite.databeans.UserBean;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.Transaction;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.MatchArg;

import java.util.*;

public class UserDAO {
	private BeanFactory< UserBean > factory;
	
	public UserDAO() throws DAOException {
		try {
			BeanTable< UserBean > table = BeanTable.getInstance(UserBean.class, "clubweb_user");
			if(! table.exists()) table.create("userId");
			table.setIdleConnectionCleanup(true);
			factory = table.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory< UserBean > getFactory() {
		return factory;
	}
	
	public UserBean lookupWithUserId(int userId) throws DAOException {
		try {
			return factory.lookup(userId);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public UserBean lookupWithEmailAddress(String emailAddress) throws DAOException {
		try {
			UserBean[] users = factory.match(MatchArg.equals("emailAddress", emailAddress));
			assert(users.length < 2);
			return (users.length == 1)? users[0]: null;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public List< UserBean > getAllUsers() throws DAOException {
		try {
			UserBean[] users = factory.match();
			Arrays.sort(users); // Sort using UserBean's compareTo()
			return Arrays.asList(users);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public UserBean create(UserBean bean) throws DAOException {
		try {
			Transaction.begin();
			if (lookupWithEmailAddress(bean.getEmailAddress()) != null)
				throw new DAOException("UserDAO: Email address already exists");
			UserBean dbUser = factory.create();
			factory.copyInto(bean, dbUser);
			Transaction.commit();
			return dbUser;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean destroy(int userId) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			factory.delete(userId);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean updatePassword(int userId, String password) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			dbUser.setPassword(password);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean updateFirstName(int userId, String firstName) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			dbUser.setFirstName(firstName);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean updateLastName(int userId, String lastName) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			dbUser.setLastName(lastName);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean updateSex(int userId, String sex) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			dbUser.setSex(sex);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	

	public boolean updateUserName(int userId, String userName) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			dbUser.setUserName(userName);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean updateUserGroup(int userId, int userGroup) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			dbUser.setUserGroup(userGroup);
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public boolean renewMembership(int userId, Date newMembershipExpirationDate) throws DAOException {
		try {
			Transaction.begin();
			UserBean dbUser = factory.lookup(userId);
			if (dbUser == null) {
				throw new DAOException("UserBean " + String.valueOf(userId) + " does not exist");
			}
			dbUser.setMembershipExpirationDateValue(newMembershipExpirationDate.getTime());
			Transaction.commit();
			return true;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public List<UserBean> getPendingMemberShip() throws DAOException {
		List<UserBean> allusers = getAllUsers();
		List<UserBean> list = new ArrayList<UserBean>(allusers.size());
		Iterator<UserBean> iter = allusers.iterator();
		while( iter.hasNext() ){
			UserBean user = iter.next();
			if( user.getUserGroup() < 1 || user.getUserGroup() > 5 )
				list.add(user);
		}
		return list;
	}
}

