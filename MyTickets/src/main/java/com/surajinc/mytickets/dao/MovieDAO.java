package com.surajinc.mytickets.dao;

import java.util.List;

import com.surajinc.mytickets.pojo.Movie;

public interface MovieDAO {

	public void addOrUpdateMovie(Movie movie);
	public List<Movie> listMovie();
	public List<Movie> listMovieByCategory(int categoryId);
//	public List<Movie> listMovieByActor(String actorName);
//	public List<Movie> listMovieByActress(String actressName);
//	public void removeMovie(Movie movie);
	public Movie getMovie(String movie);
	public Movie getMovie(int movieId);
}
