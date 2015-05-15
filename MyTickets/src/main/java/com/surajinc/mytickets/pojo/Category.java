package com.surajinc.mytickets.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category {

	public Category(){
		
	}
	
	@Id
	@Column(name="CATEGORY_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer categoryId;
	
	@Column
	private String name;
	

	@OneToMany(cascade=CascadeType.ALL)
	@Column(name="CATEGORY_ID")
	private List<Movie> movieList = new ArrayList<Movie>();


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Movie> getMovieList() {
		return movieList;
	}


	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}	
}
