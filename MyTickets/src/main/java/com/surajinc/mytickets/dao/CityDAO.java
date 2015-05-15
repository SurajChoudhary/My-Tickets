package com.surajinc.mytickets.dao;

import java.util.List;

import com.surajinc.mytickets.pojo.City;


public interface CityDAO {

//	public void addCity(City city);
	public List<City> listCity();
	public City getCity(String cityName);
}
