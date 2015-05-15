package com.surajinc.mytickets.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.CinemaDAO;
import com.surajinc.mytickets.pojo.Cinema;

@Repository
public class CinemaDAOImp implements CinemaDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> listCinema(int cityId) {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from Cinema where city_CITY_ID = :cityId");
		q.setInteger("cityId", cityId);
		return q.list();
	}

	@Override
	public Cinema getCinema(String name) {
		// TODO Auto-generated method stub
		Query q=  sessionFactory.getCurrentSession().createQuery("from Cinema where name =:name");
		q.setString("name", name);
		return (Cinema) q.uniqueResult();
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Cinema> listCinemaByMovie(int movieId) {
//		// TODO Auto-generated method stub
//		Query q= sessionFactory.getCurrentSession().createQuery("from Cinema where movieId= :movieId");
//		q.setString("movieId", String.valueOf(movieId));
//		return q.list();
//	}

	
	
}
