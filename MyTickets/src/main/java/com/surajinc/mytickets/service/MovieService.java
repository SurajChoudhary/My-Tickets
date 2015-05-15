package com.surajinc.mytickets.service;

import java.util.List;

import com.surajinc.mytickets.pojo.Movie;

public interface MovieService {

	public void addOrUpdateMovie(Movie movie);
	public List<Movie> listMovie();
	public List<Movie> listMovieByCategory(int categoryId);
	public Movie getMovie(String movie);
	public Movie getMovie(int movieId);
}
