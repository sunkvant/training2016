package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vamendrik.training.banking.daodb.AbstractDAO;
import com.vamendrik.training.banking.daodb.mapper.CityMapper;
import com.vamendrik.training.banking.daodb.mapper.CountryMapper;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Country;

public class CountryDaoImpl implements AbstractDAO<Country,Long> {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Country> getAll() {
		List<Country> country = this.jdbcTemplate.query("select * from country",new CountryMapper());
		return country;
	}

	@Override
	public Country getById(Long id) {
		Country country = this.jdbcTemplate.queryForObject("select * from country where id=?",new Object[] {id},new CountryMapper());
		return country;
	}

	@Override
	public void update(Country entity) {
		this.jdbcTemplate.update("update country set country_name=? where id=?", entity.getCountryName(),entity.getId());
	}

	@Override
	public void deleteById(Country entity) {
		jdbcTemplate.update("delete from country where id=?",entity.getId());
		
	}

	@Override
	public void insert(Country entity) {
		jdbcTemplate.update("insert into country (country_name) values (?)",entity.getCountryName());
		
	}
	
	

}
