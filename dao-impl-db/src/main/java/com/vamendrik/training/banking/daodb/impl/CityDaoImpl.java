package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.ICityDao;
import com.vamendrik.training.banking.daodb.impl.abstractions.AbstractDaoImpl;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.annotations.Entity;


@Repository
@Entity(object = "City",table="city")
public class CityDaoImpl extends AbstractDaoImpl<City,Long> implements ICityDao {


	@Override
	public void delete(City entity) {
		this.jdbcTemplate.update("update city set is_delete=true where id=?", entity.getId());

	}


	@Override
	public List<City> getAllByCountryId(Long countryId) {
		
		return this.getAllByFieldValue("select public.city.* from public.city join public.country on public.city.country_id=public.country.id where public.country.id=? order by public.city.id asc", new Object[] { countryId });
		
	}

}
