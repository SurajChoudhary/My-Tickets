package com.surajinc.mytickets.pojo;

import java.io.Serializable;
import java.util.ArrayList;
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

@Entity
@Table(name="Cinema")
public class Cinema implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public Cinema(){
		
	}
	
	@Id
	@Column(name="CINEMA_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cinemaId;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private String phone;
	
	@ManyToOne
	private City city;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CINEMA_ID")
	private List<MovieShowing> movieShowings= new ArrayList<MovieShowing>();
	
	
	
	public Integer getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<MovieShowing> getMovieShowings() {
		return movieShowings;
	}

	public void setMovieShowings(List<MovieShowing> movieShowings) {
		this.movieShowings = movieShowings;
	}

	
}
