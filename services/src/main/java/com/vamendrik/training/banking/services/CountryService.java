package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Country;

public interface CountryService {

	public List<Country> getAll();
	public Long add(String countryName);
	public void delete(Country city);
	public void save(Country city);
	public Country get(Long id);
	
}
