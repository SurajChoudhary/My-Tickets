package com.surajinc.mytickets.serviceImp;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surajinc.mytickets.dao.ShowtimeDAO;
import com.surajinc.mytickets.form.AddShowtimeForm;
import com.surajinc.mytickets.pojo.MovieShowing;
import com.surajinc.mytickets.pojo.Showtime;
import com.surajinc.mytickets.service.ShowtimeService;
import com.surajinc.mytickets.utility.DateRange;

@Service
public class ShowtimeServiceImp implements ShowtimeService {

	
	@Autowired
	ShowtimeDAO showtimeDAO;

	@Override
	@Transactional
	public Showtime getShowtime(int showtimeId) {
		// TODO Auto-generated method stub
		return showtimeDAO.getShowtime(showtimeId);
	}
	
	@Override
	@Transactional
	public void addShowtime(AddShowtimeForm form) {
		// TODO Auto-generated method stub

		MovieShowing showing = form.getMovieShowing();

		LocalDate start = LocalDate.fromDateFields(showing.getMovieStartDate());
		LocalDate end = LocalDate.fromDateFields(showing.getMovieEndDate());
		for (LocalDate date : new DateRange(start, end)) {
				System.out.println(date);

			for (String time : form.getTimings()) {
				Showtime showtime = new Showtime();
				showtime.setShowStartTime(time);
				showtime.setTicketPrice(form.getTicketPrice());
				showtime.setAvailableSeats(20);
				showtime.setMovieShowing(showing);
				showtime.setDate(date.toDate());
				showtimeDAO.add(showtime);
			}
		}

	}
	
	@Override
	@Transactional
	public List<Showtime> listShowtimes(String dateString, MovieShowing movieShowing) {
		// TODO Auto-generated method stub
		String[] parts = dateString.split("/");
		System.out.println(dateString);
		System.out.println(parts[2]+parts[1]+parts[0]);
		String date= parts[2]+"-"+parts[0]+"-"+parts[1];
		return showtimeDAO.listShowtime(date, movieShowing.getMovieShowingId());
	}

}
