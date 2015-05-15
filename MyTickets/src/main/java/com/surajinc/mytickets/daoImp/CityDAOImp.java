package com.surajinc.mytickets.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.CityDAO;
import com.surajinc.mytickets.pojo.City;

@Repository
public class CityDAOImp implements CityDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<City> listCity() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from City").list();
	}

	@Override
	public City getCity(String cityName) {
		// TODO Auto-generated method stub
		Query q=  sessionFactory.getCurrentSession().createQuery("from City where cityName =:name");
		q.setString("name", cityName);
		return (City) q.uniqueResult();
	}
}
