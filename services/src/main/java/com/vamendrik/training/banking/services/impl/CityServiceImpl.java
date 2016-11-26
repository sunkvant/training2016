package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daoapi.ICityDao;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.services.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Inject
	ICityDao cityDao;
	
	@Override
	public List<City> getAll() {
		
		
		return cityDao.getAll();
		
	}
	
	@Override
	public Long add(String cityName,Long coutryId) {
		
		City city=new City();
		
		city.setCityName(cityName);
		city.setCoutryId(coutryId);
		
		return cityDao.insert(city);
	}
	
	@Override
	public void delete(City city) {
		
		cityDao.delete(city);
		
	}
	
	@Override
	public void save(City city) {
		
		
		if (cityDao.getById(city.getId())==null) {
			
			cityDao.insert(city);
			
		} else {
			
			cityDao.update(city);
			
		}
	}
	
	@Override
	public City get(Long id) {
		
		
		return cityDao.getById(id);
		
		
	}
	
}