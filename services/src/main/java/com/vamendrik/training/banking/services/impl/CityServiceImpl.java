package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.CityDao;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Country;

@Service
public class CityServiceImpl {
	
	@Inject
	CityDao cityDao;
	
	public List<City> getAll() {
		
		
		return cityDao.getAll();
		
	}
	
	public void add(String cityName,Long coutryId) {
		
		City city=new City();
		
		city.setCityName(cityName);
		city.setCoutryId(coutryId);
		
		cityDao.insert(city);
	}
	
	public void delete(City city) {
		
		cityDao.delete(city);
		
	}
	
	public void save(City city) {
		
		
		if (cityDao.getById(city.getId())==null) {
			
			cityDao.insert(city);
			
		} else {
			
			cityDao.update(city);
			
		}
	}
	
	public City get(Long id) {
		
		
		return cityDao.getById(id);
		
		
	}
	
}