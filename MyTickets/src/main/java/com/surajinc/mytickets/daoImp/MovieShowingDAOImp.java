package com.surajinc.mytickets.daoImp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.MovieShowingDAO;
import com.surajinc.mytickets.pojo.MovieShowing;

@Repository
public class MovieShowingDAOImp implements MovieShowingDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addMovieShowing(MovieShowing movieShowing) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(movieShowing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieShowing> listMovieShowing() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from MovieShowing order by cinema_CINEMA_ID").list();
	}

	@Override
	public MovieShowing getMovieShowing(int movieShowingId) {
		// TODO Auto-generated method stub
		return (MovieShowing) sessionFactory.getCurrentSession().get(MovieShowing.class, movieShowingId); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieShowing> listMovieShowingByCinema(int cinemaId) {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from MovieShowing where cinema_CINEMA_ID= :cinemaId");
		q.setInteger("cinemaId", cinemaId);
		return q.list();
	}

	@Override
	public MovieShowing getMovieShowing(int movieId, int cinemaId) {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from MovieShowing where cinema_CINEMA_ID= :cinemaId and movie_MOVIE_ID =:movieId");
		q.setInteger("movieId", movieId);
		q.setInteger("cinemaId", cinemaId);
		MovieShowing movieShowing = (MovieShowing) q.uniqueResult();
		return movieShowing;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieShowing> listCurrentMovie() {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from MovieShowing where movieStartDate <:date and movieEndDate >:date");
		 Date currentDate =new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 q.setString("date", sdf.format(currentDate));
		 
		 System.out.println(q.list().get(0));
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieShowing> listUpcomingMovie() {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from MovieShowing where movieEndDate >:date");
		 Date currentDate =new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 q.setString("date", sdf.format(currentDate));
		 
		 System.out.println(q.list().get(0));
		return q.list();
	}
	
}
