package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Country;

public interface ICountryService extends IAbstractService<Country,Long> {

	public Country getByCountryName(String countryName);
	public Long add(String countryName);
	
}
