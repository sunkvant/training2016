package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.CountryDao;
import com.vamendrik.training.banking.datamodel.Country;


@Service
public class CountryServiceImpl {

	@Inject
	CountryDao countryDao;
	
	public List<Country> getAll() {
		
		
		return countryDao.getAll();
		
	}
	
	public void add(String countryName) {
		
		Country country=new Country();
		
		country.setCountryName(countryName);
		
		countryDao.insert(country);
	}
	
	public void delete(Country country) {
		
		countryDao.delete(country);
		
	}
	
	public void save(Country country) {
		
		
		if (countryDao.getById(country.getId())==null) {
			
			countryDao.insert(country);
			
		} else {
			
			countryDao.update(country);
			
		}
		
		
	}
	
	
	public Country get(Long id) {
		
		
		return countryDao.getById(id);
		
		
	}
	

}
