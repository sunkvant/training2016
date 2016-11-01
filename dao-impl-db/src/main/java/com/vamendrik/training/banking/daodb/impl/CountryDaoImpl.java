package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.CountryDao;
import com.vamendrik.training.banking.daodb.mapper.CountryMapper;
import com.vamendrik.training.banking.datamodel.Country;

@Repository
public class CountryDaoImpl implements CountryDao {

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
	public void delete(Country entity) {
		jdbcTemplate.update("delete from country where id=?",entity.getId());
		
	}

	@Override
	public void insert(Country entity) {
		jdbcTemplate.update("insert into country (country_name) values (?)",entity.getCountryName());
		
	}
	
	

}
