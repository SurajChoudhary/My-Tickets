package com.surajinc.mytickets.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.UserDAO;
import com.surajinc.mytickets.pojo.User;

@Repository
public class UserDAOImp implements UserDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public User checkUser(String userId) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@Override
	public void removeUser(String firstName) {
		// TODO Auto-generated method stub
		  Query q= sessionFactory.getCurrentSession().createQuery("from User where firstName =:name");
		  q.setString("name", firstName);
	      User user = (User)q.uniqueResult();  
		  if (null != user)
	            sessionFactory.getCurrentSession().delete(user);
	 }

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}
}
