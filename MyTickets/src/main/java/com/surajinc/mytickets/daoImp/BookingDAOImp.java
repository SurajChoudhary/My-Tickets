package com.surajinc.mytickets.daoImp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.BookingsDAO;
import com.surajinc.mytickets.pojo.Bookings;
import com.surajinc.mytickets.pojo.Payment;
@Repository
public class BookingDAOImp implements BookingsDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addBooking(Bookings booking) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(booking);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bookings> listBookings(String userId) {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from Bookings where user_USER_ID =:userId");
		q.setString("userId", userId);
		return q.list();
	}

	@Override
	public Payment paymentDetails(int bookingId) {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from Bookings where bookingId =:bookingId");
		q.setString("bookingId", String.valueOf(bookingId));
		return (Payment) q.uniqueResult();
	}

	@Override
	public Bookings lastBooking() {
		// TODO Auto-generated method stub
		Query q= sessionFactory.getCurrentSession().createQuery("from Bookings order by BOOKING_ID DESC");
		q.setMaxResults(1);
		return (Bookings) q.uniqueResult();
	}

	
}
