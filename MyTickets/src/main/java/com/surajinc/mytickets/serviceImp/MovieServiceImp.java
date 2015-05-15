package com.surajinc.mytickets.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surajinc.mytickets.dao.MovieDAO;
import com.surajinc.mytickets.pojo.Movie;
import com.surajinc.mytickets.service.MovieService;

@Service
public class MovieServiceImp implements MovieService{

	@Autowired
	private MovieDAO movieDAO;
	
	@Override
	@Transactional
	public void addOrUpdateMovie(Movie movie) {
		// TODO Auto-generated method stub

		//fetch data from API
		//add movie to a category
		//assignment of ID
		
		movieDAO.addOrUpdateMovie(movie);
		
	}
	
//	@Override
//	@Transactional
//	public Movie[] listMovieInArray() {
//		// TODO Auto-generated method stub
//		
//		List<Movie> movieList= movieDAO.listMovie();
//		Movie[] movie= new Movie[movieList.size()];
//		for(int i=0;i<movieList.size();i++)
//				movie[i]=movieList.get(i);
//		
//		return movie;
//	}

	@Override
	@Transactional
	public List<Movie> listMovie() {
		// TODO Auto-generated method stub
		return movieDAO.listMovie();
	}

	@Override
	@Transactional
	public List<Movie> listMovieByCategory(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Movie getMovie(String movie) {
		// TODO Auto-generated method stub
		return movieDAO.getMovie(movie);
	}
	
	@Transactional
	@Override
	public Movie getMovie(int movieId) {
		// TODO Auto-generated method stub
		return movieDAO.getMovie(movieId);
	}

	

	

	

}
