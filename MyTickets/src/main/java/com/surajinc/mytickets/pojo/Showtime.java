package com.surajinc.mytickets.pojo;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Showtime")
public class Showtime {

	public Showtime(){
		
	}
	
	@Id
	@Column(name="SHOWTIME_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer showtimeId;
	
	@Column
	private String showStartTime;
	
	@Column
	private Double ticketPrice;
	
	@Column
	private Integer availableSeats;
	
	@Column
	private Date date;
	
	@ManyToOne
	private MovieShowing movieShowing;

	public Integer getShowtimeId() {
		return showtimeId;
	}

	public void setShowtimeId(Integer showtimeId) {
		this.showtimeId = showtimeId;
	}

	public String getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(String showStartTime) {
		this.showStartTime = showStartTime;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MovieShowing getMovieShowing() {
		return movieShowing;
	}

	public void setMovieShowing(MovieShowing movieShowing) {
		this.movieShowing = movieShowing;
	}	
}
