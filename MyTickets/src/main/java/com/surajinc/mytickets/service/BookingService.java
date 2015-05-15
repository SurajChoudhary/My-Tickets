package com.surajinc.mytickets.service;

import java.util.List;

import com.surajinc.mytickets.form.BookMovieForm;
import com.surajinc.mytickets.pojo.Bookings;
import com.surajinc.mytickets.pojo.User;

public interface BookingService {

	public void addBooking(BookMovieForm form, User user);
	public List<Bookings> listBooking(String userId);
}
