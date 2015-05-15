package com.surajinc.mytickets.service;

import java.util.List;

import com.surajinc.mytickets.form.AddShowtimeForm;
import com.surajinc.mytickets.pojo.MovieShowing;
import com.surajinc.mytickets.pojo.Showtime;

public interface ShowtimeService {

	public Showtime getShowtime(int showtimeId);
	public void addShowtime(AddShowtimeForm addShowtimeForm);
	public List<Showtime> listShowtimes(String date, MovieShowing movieShowing);
}
