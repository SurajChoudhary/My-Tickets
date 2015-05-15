package com.surajinc.mytickets.service;

import java.util.List;

import com.surajinc.mytickets.pojo.City;

public interface CityService {
	
	public List<City> listCity();
	public City getCity(String cityName);
}
