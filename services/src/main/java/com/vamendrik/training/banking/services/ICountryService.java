package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Country;

public interface ICountryService extends IAbstractService<Country,Long> {

	public Long add(String countryName);
	
}
