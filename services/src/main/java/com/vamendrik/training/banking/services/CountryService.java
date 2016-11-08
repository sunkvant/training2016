package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Country;

public interface CountryService extends AbstractService<Country,Long> {

	public Long add(String countryName);
	
}
