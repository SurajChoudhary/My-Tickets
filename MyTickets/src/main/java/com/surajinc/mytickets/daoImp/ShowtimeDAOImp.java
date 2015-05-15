package com.surajinc.mytickets.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.ShowtimeDAO;
import com.surajinc.mytickets.pojo.Showtime;

@Repository
public class ShowtimeDAOImp implements ShowtimeDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void add(Showtime showtime) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(showtime);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Showtime> listShowtime(String date, int movieShowingId) {
		// TODO Auto-generated method stub
		Query q = sessionFactory.getCurrentSession()
				.createQuery(
						"from Showtime where date= :date and movieShowing_MOVIESHOWING_ID =:movieShowingId ");
		q.setString("date", date);
		q.setInteger("movieShowingId", movieShowingId);
		return q.list();
	}

	@Override
	public Showtime getShowtime(int showtimeId) {
		// TODO Auto-generated method stub
		return (Showtime) sessionFactory.getCurrentSession().get(Showtime.class, showtimeId);
	}

	@Override
	public void updateAvailability(Showtime showtime) {
		// TODO Auto-generated method stub
		 sessionFactory.getCurrentSession().update(showtime);
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Showtime> listShowtimes(int movieId) {
	// // TODO Auto-generated method stub
	// Query q=
	// sessionFactory.getCurrentSession().createQuery("from Showtime where movieId= : movieId");
	// q.setString("movieId", String.valueOf(movieId));
	// return q.list();
	// }
}
