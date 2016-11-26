package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Country;

public interface ICountryDao extends IAbstractDao<Country,Long> {
	
	public Country getByCountryName(String countryName);

}
