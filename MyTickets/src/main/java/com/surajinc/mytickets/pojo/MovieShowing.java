package com.surajinc.mytickets.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="MovieShowing")
public class MovieShowing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieShowing(){
		
	}
	
	@Id
	@Column(name="MOVIESHOWING_ID", unique = true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer movieShowingId;
	
	@Column
	@NotNull
	@Future(message="Please make a valid selection")
	private Date movieStartDate;
	
	@Column
	@NotNull
	@Future(message="Please make a valid selection")
	private Date movieEndDate;
	
	@ManyToOne
	private Cinema cinema;
	
	@ManyToOne
	private Movie movie;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="MOVIEID")
	private List<Showtime> showList = new ArrayList<Showtime>();
	
//	private City city;
	
	public Integer getMovieShowingId() {
		return movieShowingId;
	}

	public void setMovieShowingId(Integer movieShowingId) {
		this.movieShowingId = movieShowingId;
	}

	public Date getMovieStartDate() {
		return movieStartDate;
	}

	public void setMovieStartDate(Date movieStartDate) {
		this.movieStartDate = movieStartDate;
	}

	public Date getMovieEndDate() {
		return movieEndDate;
	}

	public void setMovieEndDate(Date movieEndDate) {
		this.movieEndDate = movieEndDate;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Showtime> getShowList() {
		return showList;
	}

	public void setShowList(List<Showtime> showList) {
		this.showList = showList;
	}

}
