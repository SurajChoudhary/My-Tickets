package com.surajinc.mytickets.daoImp;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.TicketDAO;
import com.surajinc.mytickets.pojo.Ticket;

@Repository
public class TicketDAOImp implements TicketDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(ticket);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Ticket> listTicket(int showtimeId) {
//		// TODO Auto-generated method stub
//		Query q= sessionFactory.getCurrentSession().createQuery("from Ticket where showtimeId= : showtimeId");
//		q.setString("showtimeId", String.valueOf(showtimeId));
//		return q.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Ticket> listTicketForBooking(int bookingId) {
//		// TODO Auto-generated method stub
//		Query q= sessionFactory.getCurrentSession().createQuery("from Ticket where bookingId= : bookingId");
//		q.setString("bookingId", String.valueOf(bookingId));
//		return q.list();
//	}

}
