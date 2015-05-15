package com.surajinc.mytickets.dao;

import java.util.List;

import com.surajinc.mytickets.pojo.Cinema;

public interface CinemaDAO {


	public List<Cinema> listCinema(int cityId);
//	public List<Cinema> listCinemaByMovie(int movieId);
	public Cinema getCinema(String name);
}
