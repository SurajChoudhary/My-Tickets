package com.surajinc.mytickets.dao;

import java.util.List;

import com.surajinc.mytickets.pojo.MovieShowing;

public interface MovieShowingDAO {

	public void addMovieShowing(MovieShowing movieShowing);
	public List<MovieShowing> listMovieShowing();
	public List<MovieShowing> listMovieShowingByCinema(int cinemaId);
	public MovieShowing getMovieShowing(int movieShowingId);
	public MovieShowing getMovieShowing(int movieId, int cinemaId);
	public List<MovieShowing> listCurrentMovie();
	public List<MovieShowing> listUpcomingMovie();
}
