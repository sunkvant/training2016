package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vamendrik.training.banking.daodb.AbstractDAO;
import com.vamendrik.training.banking.daodb.mapper.CityMapper;
import com.vamendrik.training.banking.daodb.mapper.ClientMapper;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Client;

public class CityDaoImpl implements AbstractDAO<City,Long> {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<City> getAll() {
		List<City> cities = this.jdbcTemplate.query("select * from city",new CityMapper());
		return cities;
	}

	@Override
	public City getById(Long id) {
		City city = this.jdbcTemplate.queryForObject("select * from city where id=?",new Object[] {id},new CityMapper());
		return city;
	}

	@Override
	public void update(City entity) {
		this.jdbcTemplate.update("update city set city_name=?,country_id=? where id=?", entity.getCityName(),entity.getCountryId(),entity.getId());
		
	}

	@Override
	public void deleteById(City entity) {
		jdbcTemplate.update("delete from city where id=?",entity.getId());
		
	}

	@Override
	public void insert(City entity) {
		jdbcTemplate.update("insert into city (city_name,country_id) values (?,?)",entity.getCityName(),entity.getCountryId());
		
	}
	
	
	

}
