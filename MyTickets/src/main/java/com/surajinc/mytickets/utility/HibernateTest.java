package com.surajinc.mytickets.utility;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.surajinc.mytickets.pojo.City;
import com.surajinc.mytickets.pojo.User;

public class HibernateTest {

	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		User user= new User();
		user.setEmail("dummy@gmail.com");
		user.setFirstName("dummy");
		user.setLastName("dummy");
		user.setPassword("dummy");
		user.setDob(new Date());
		user.setRole("customer");
//		User user1= new User();
//		user1.setEmail("test@gmail.com");
//		user1.setFirstName("test");
//		user1.setLastName("test");
//		user1.setPassword("test");
//		user1.setDob(new Date());
//		user1.setRole("customer");
		
		City city = new City();
		city.setCityName("Paris");
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Session session= sessionFactory.openSession();
		session.beginTransaction();
//		session.save(user);
		session.save(city);
		session.getTransaction().commit();
	}
}
