package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.City;

public interface ICityService extends IAbstractService<City,Long> {
	
	public Long add(String cityName,Long coutryId);

}
