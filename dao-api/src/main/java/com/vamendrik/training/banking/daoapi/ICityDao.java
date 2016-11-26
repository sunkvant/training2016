package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.City;

public interface ICityDao extends IAbstractDao<City, Long> {
	
	public List<City> getAllByCountryId(Long countryId);

}
