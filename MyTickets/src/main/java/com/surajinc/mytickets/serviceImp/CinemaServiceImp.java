package com.surajinc.mytickets.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surajinc.mytickets.dao.CinemaDAO;
import com.surajinc.mytickets.pojo.Cinema;
import com.surajinc.mytickets.service.CinemaService;


@Service
public class CinemaServiceImp implements CinemaService{

	@Autowired
	CinemaDAO cinemaDAO;
	
	@Override
	@Transactional
	public List<Cinema> listCinemaByCity(int cityId) {
		// TODO Auto-generated method stub
		return cinemaDAO.listCinema(cityId);
	}

	@Override
	@Transactional
	public Cinema getCinema(String name) {
		// TODO Auto-generated method stub
		return cinemaDAO.getCinema(name);
	}

	

	
	
}
