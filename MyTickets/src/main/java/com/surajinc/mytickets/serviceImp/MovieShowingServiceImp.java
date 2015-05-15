package com.surajinc.mytickets.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surajinc.mytickets.dao.MovieShowingDAO;
import com.surajinc.mytickets.pojo.MovieShowing;
import com.surajinc.mytickets.service.MovieShowingService;

@Service
public class MovieShowingServiceImp implements MovieShowingService{

	@Autowired
	MovieShowingDAO movieShowingDAO;
	
	@Override
	@Transactional
	public void addMovieShowing(MovieShowing movieShowing) {
		// TODO Auto-generated method stub
		movieShowingDAO.addMovieShowing(movieShowing);
	}

	@Override
	@Transactional
	public List<MovieShowing> listMovieShowing() {
		// TODO Auto-generated method stub
		return movieShowingDAO.listMovieShowing();
	}

	@Override
	@Transactional
	public MovieShowing getMovieShowing(int movieShowingId) {
		// TODO Auto-generated method stub
		return movieShowingDAO.getMovieShowing(movieShowingId);
	}

	@Override
	@Transactional
	public List<MovieShowing> listMovieShowingByCinema(int cinemaId) {
		// TODO Auto-generated method stub
		return movieShowingDAO.listMovieShowingByCinema(cinemaId);
	}

	@Override
	@Transactional
	public MovieShowing getMovieShowing(int movieId, int cinemaId) {
		// TODO Auto-generated method stub
		return movieShowingDAO.getMovieShowing(movieId, cinemaId);
	}
	
	
	@Override
	@Transactional
	public List<MovieShowing> listCurrentMovies() {
		// TODO Auto-generated method stub
		return movieShowingDAO.listCurrentMovie();
	}

	@Override
	@Transactional
	public List<MovieShowing> listUpcomingMovie() {
		// TODO Auto-generated method stub
		return movieShowingDAO.listUpcomingMovie();
	}
	
}
