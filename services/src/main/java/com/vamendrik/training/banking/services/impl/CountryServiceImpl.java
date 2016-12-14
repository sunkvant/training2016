package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daoapi.ICountryDao;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.services.ICountryService;


@Service
public class CountryServiceImpl implements ICountryService {

	@Inject
	ICountryDao countryDao;
	
	@Override
	public List<Country> getAll() {
		
		
		return countryDao.getAll();
		
	}
	
	@Override
	public Long add(String countryName) {
		
		Country country=new Country();
		
		country.setCountryName(countryName);
		
		return countryDao.insert(country);
	}
	
	@Override
	public void delete(Country country) {
		
		countryDao.delete(country);
		
	}
	
	@Override
	public void save(Country country) {
		
		
		if (countryDao.getById(country.getId())==null) {
			
			countryDao.insert(country);
			
		} else {
			
			countryDao.update(country);
			
		}
		
		
	}
	
	@Override
	public Country get(Long id) {
		
		
		return countryDao.getById(id);
		
		
	}

	@Override
	public Country getByCountryName(String countryName) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
