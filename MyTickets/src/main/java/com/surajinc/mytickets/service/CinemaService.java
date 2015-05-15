package com.surajinc.mytickets.service;

import java.util.List;

import com.surajinc.mytickets.pojo.Cinema;

public interface CinemaService {

	public List<Cinema> listCinemaByCity(int cityId);
	public Cinema getCinema(String name);
}
