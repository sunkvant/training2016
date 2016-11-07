package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.City;

public interface CityService {
	
	public List<City> getAll();
	public Long add(String cityName,Long coutryId);
	public void delete(City city);
	public void save(City city);
	public City get(Long id);

}
