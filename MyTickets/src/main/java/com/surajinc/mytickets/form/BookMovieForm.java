package com.surajinc.mytickets.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.surajinc.mytickets.pojo.MovieShowing;
import com.surajinc.mytickets.pojo.Payment;
import com.surajinc.mytickets.pojo.Showtime;

public class BookMovieForm {
	
	@NotEmpty
	private String cityName;
	
	@NotEmpty
	private String cinemaName;
	
	@NotNull
	private MovieShowing movieShowing;
	
	@NotNull
	private Showtime showtime;
	
	@NotEmpty
	private int numberOfTickets;
	
	@NotNull
	private Payment payment;
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public MovieShowing getMovieShowing() {
		return movieShowing;
	}

	public void setMovieShowing(MovieShowing movieShowing) {
		this.movieShowing = movieShowing;
	}

	public Showtime getShowtime() {
		return showtime;
	}

	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	
}
