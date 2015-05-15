package com.surajinc.mytickets.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.MovieDAO;
import com.surajinc.mytickets.pojo.Movie;

@Repository
public class MovieDAOImp implements MovieDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addOrUpdateMovie(Movie movie) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(movie);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> listMovie() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Movie").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> listMovieByCategory(int categoryId) {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from Movie where categoryId= : categoryId");
		q.setString("categoryId", String.valueOf(categoryId));
		return q.list();
	}

	@Override
	public Movie getMovie(String movieName) {
		// TODO Auto-generated method stub
		Query q=  sessionFactory.getCurrentSession().createQuery("from Movie where name =:name");
		q.setString("name", movieName);
		return (Movie) q.uniqueResult();
	}


	@Override
	public Movie getMovie(int movieId) {
		// TODO Auto-generated method stub
		return (Movie) sessionFactory.getCurrentSession().get(Movie.class, movieId);
	}
}
