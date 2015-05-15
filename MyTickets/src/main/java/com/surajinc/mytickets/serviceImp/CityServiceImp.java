package com.surajinc.mytickets.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surajinc.mytickets.dao.CityDAO;
import com.surajinc.mytickets.pojo.City;
import com.surajinc.mytickets.service.CityService;

@Service
public class CityServiceImp implements CityService{

	@Autowired
	CityDAO cityDAO;
	
	@Transactional
	@Override
	public List<City> listCity() {
		return cityDAO.listCity();
	}

	@Override
	@Transactional
	public City getCity(String cityName) {
		// TODO Auto-generated method stub
		return cityDAO.getCity(cityName);
	}

}
