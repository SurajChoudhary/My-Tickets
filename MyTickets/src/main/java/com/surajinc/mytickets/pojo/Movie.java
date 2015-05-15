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
@Table(name="Movie")
public class Movie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Movie(){
		
	}
	
	@Id
	@Column(name="MOVIE_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer movieId;
	
	@Column
	private String name;
	
	@Column
	private String actor;
	
	@Column
	private String actress;

	@ManyToOne
	private Category category;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="MOVIESHOWING_ID")
	private List<MovieShowing> movieShowings= new ArrayList<MovieShowing>();

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="MOVIEID")
	private List<Comments> movieComments= new ArrayList<Comments>();

	public List<Comments> getMovieComments() {
		return movieComments;
	}


	public void setMovieComments(List<Comments> movieComments) {
		this.movieComments = movieComments;
	}


	public List<MovieShowing> getMovieShowings() {
		return movieShowings;
	}


	public void setMovieShowings(List<MovieShowing> movieShowings) {
		this.movieShowings = movieShowings;
	}


	public void setMovieShowings(ArrayList<MovieShowing> movieShowings) {
		this.movieShowings = movieShowings;
	}


	public Integer getMovieId() {
		return movieId;
	}


	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getActor() {
		return actor;
	}


	public void setActor(String actor) {
		this.actor = actor;
	}


	public String getActress() {
		return actress;
	}


	public void setActress(String actress) {
		this.actress = actress;
	}

	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

}