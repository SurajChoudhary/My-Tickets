package com.surajinc.mytickets.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ticket")
public class Ticket {

	public Ticket(){
		
	}
	
	@Id
	@Column(name="TICKET_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ticketId;

	@ManyToOne
	private Bookings booking;
	
	@OneToOne
	@JoinColumn(name="SHOWTIME_ID")
	private Showtime showtime;

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Bookings getBooking() {
		return booking;
	}

	public void setBooking(Bookings booking) {
		this.booking = booking;
	}

	public Showtime getShowtime() {
		return showtime;
	}

	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}	
}
