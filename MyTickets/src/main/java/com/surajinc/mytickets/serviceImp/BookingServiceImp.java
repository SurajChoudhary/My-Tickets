package com.surajinc.mytickets.serviceImp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surajinc.mytickets.dao.BookingsDAO;
import com.surajinc.mytickets.dao.PaymentDAO;
import com.surajinc.mytickets.dao.ShowtimeDAO;
import com.surajinc.mytickets.dao.TicketDAO;
import com.surajinc.mytickets.form.BookMovieForm;
import com.surajinc.mytickets.pojo.Bookings;
import com.surajinc.mytickets.pojo.Payment;
import com.surajinc.mytickets.pojo.Showtime;
import com.surajinc.mytickets.pojo.Ticket;
import com.surajinc.mytickets.pojo.User;
import com.surajinc.mytickets.service.BookingService;

@Service
public class BookingServiceImp implements BookingService {

	@Autowired
	BookingsDAO bookingsDAO;

	@Autowired
	PaymentDAO paymentDAO;

	@Autowired
	TicketDAO ticketDAO;
	
	@Autowired
	ShowtimeDAO showtimeDAO;

	@Transactional
	@Override
	public void addBooking(BookMovieForm form, User user) {
		// TODO Auto-generated method stub

		Payment payment = form.getPayment();
//		payment.setPaymentId(103);
		paymentDAO.addPayment(payment);

		//retrieve payment object 
		Payment retrievedPayment= paymentDAO.lastPayment();
		
		Bookings booking = new Bookings();
//		booking.setBookingId(107);
		booking.setNumberOfTickets(form.getNumberOfTickets());
		booking.setBookingDate(new Date());
		booking.setPayment(retrievedPayment);
		booking.setUser(user);
		bookingsDAO.addBooking(booking);
		
		Bookings retrievedBooking= bookingsDAO.lastBooking();
		
//		int k=205;
		Showtime showtime = form.getShowtime();
		for (int i = 0; i < form.getNumberOfTickets(); i++) {
			Ticket ticket = new Ticket();
//			ticket.setTicketId(k++);
			ticket.setBooking(retrievedBooking);
			ticket.setShowtime(showtime);
			ticketDAO.addTicket(ticket);
		}

		//DECREASE AVAILABILITY BY NUMBEROFTICKETS
		showtime.setAvailableSeats(showtime.getAvailableSeats()-form.getNumberOfTickets());
		showtimeDAO.updateAvailability(showtime);
	}

	@Override
	@Transactional
	public List<Bookings> listBooking(String userId) {
		// TODO Auto-generated method stub
		return bookingsDAO.listBookings(userId);
	}

}
