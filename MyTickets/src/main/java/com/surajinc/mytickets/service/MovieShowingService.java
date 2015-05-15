package com.surajinc.mytickets.service;

import java.util.List;

import com.surajinc.mytickets.pojo.MovieShowing;

public interface MovieShowingService {

	public void addMovieShowing(MovieShowing movieShowing);
	public List<MovieShowing> listMovieShowing();
	public MovieShowing getMovieShowing(int movieShowingId);
	public List<MovieShowing> listMovieShowingByCinema(int cinemaId);
	public MovieShowing getMovieShowing(int movieId, int cinemaId);
	public List<MovieShowing> listCurrentMovies();
	public List<MovieShowing> listUpcomingMovie();
}
