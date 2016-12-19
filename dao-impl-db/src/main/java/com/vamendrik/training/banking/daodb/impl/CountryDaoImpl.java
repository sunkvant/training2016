package com.vamendrik.training.banking.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.ICountryDao;
import com.vamendrik.training.banking.daodb.impl.abstractions.AbstractDaoImpl;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Repository
@Entity(object = "Country",table="country")
public class CountryDaoImpl extends AbstractDaoImpl<Country, Long> implements ICountryDao {

	@Override
	public void delete(Country entity) {
		jdbcTemplate.update("update country set is_delete=true where id=?", entity.getId());

	}

	@Override
	public Country getByCountryName(String countryName) {
		
		return this.getByFieldValue("select * from country where country_name=?", new Object[] { countryName });
		
	}

}
