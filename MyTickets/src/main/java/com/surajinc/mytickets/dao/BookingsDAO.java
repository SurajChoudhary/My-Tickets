package com.surajinc.mytickets.dao;

import java.util.List;

import com.surajinc.mytickets.pojo.Bookings;
import com.surajinc.mytickets.pojo.Payment;

public interface BookingsDAO {

	public void addBooking(Bookings booking);
	public List<Bookings> listBookings(String userId);
	public Payment paymentDetails(int bookingId);
	public Bookings lastBooking();
}
