package com.surajinc.mytickets.dao;

import java.util.List;

import com.surajinc.mytickets.pojo.Showtime;

public interface ShowtimeDAO {
	
	public void add(Showtime showtime);
	public List<Showtime> listShowtime(String date, int movieShowingId);
	public Showtime getShowtime(int showtimeId);
//	public void removeShowtime(int showtimeId, int movieId);
	public void updateAvailability(Showtime showtime);
}
