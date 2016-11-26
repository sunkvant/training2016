package com.vamendrik.training.banking.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.ICityDao;
import com.vamendrik.training.banking.daodb.mapper.CityMapper;
import com.vamendrik.training.banking.datamodel.City;

@Repository
public class CityDaoImpl implements ICityDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<City> getAll() {
		try {
			List<City> cities = this.jdbcTemplate.query("select * from city", new CityMapper());
			return cities;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<City>(0);

		}
	}

	@Override
	public City getById(Long id) {
		try {
			City city = this.jdbcTemplate.queryForObject("select * from city where id=?", new Object[] { id },
					new CityMapper());
			return city;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(City entity) {
		this.jdbcTemplate.update("update city set city_name=?,country_id=?,is_delete=? where id=?",
				entity.getCityName(), entity.getCountryId(), entity.isDelete(), entity.getId());

	}

	@Override
	public void delete(City entity) {
		jdbcTemplate.update("update city set is_delete=true where id=?", entity.getId());

	}

	@Override
	public Long insert(final City entity) {

		final String INSERT_SQL = "insert into city (city_name,country_id,is_delete) values (?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getCityName());
				ps.setLong(2, entity.getCountryId());
				ps.setBoolean(3, false);

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public List<City> getAllByCountryId(Long countryId) {
		try {
			List<City> cities = this.jdbcTemplate.query(
					"select public.city.* from public.city join public.country on public.city.country_id=public.country.id where public.country.id=?",
					new CityMapper());
			return cities;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<City>(0);

		}
	}

}
