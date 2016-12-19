package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.vamendrik.training.banking.daoapi.ICityDao;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Component
public class TestAnnotation {
	
	@Inject
	private ICityDao cityDao;
	
	public List<City> rt() throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException{
		
		return cityDao.getAllByCountryId(1l);
		
	}

}
