package com.surajinc.mytickets.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.surajinc.mytickets.pojo.MovieShowing;

public class AddShowtimeForm {

	
	private MovieShowing movieShowing;
	
	@NotNull
	private double ticketPrice;
	
	@NotEmpty
	private String [] timings;

	public MovieShowing getMovieShowing() {
		return movieShowing;
	}

	public void setMovieShowing(MovieShowing movieShowing) {
		this.movieShowing = movieShowing;
	}
	
	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String[] getTimings() {
		return timings;
	}

	public void setTimings(String[] timings) {
		this.timings = timings;
	}

}
