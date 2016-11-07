package com.vamendrik.training.banking.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.CountryDao;
import com.vamendrik.training.banking.daodb.mapper.CountryMapper;
import com.vamendrik.training.banking.datamodel.Country;

@Repository
public class CountryDaoImpl implements CountryDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from country", Long.class);
	}

	@Override
	public List<Country> getAll() {
		try {
			List<Country> country = this.jdbcTemplate.query("select * from country", new CountryMapper());
			return country;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public Country getById(Long id) {
		try {
			Country country = this.jdbcTemplate.queryForObject("select * from country where id=?", new Object[] { id },
					new CountryMapper());
			return country;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(Country entity) {
		this.jdbcTemplate.update("update country set country_name=? where id=?", entity.getCountryName(),
				entity.getId());
	}

	@Override
	public void delete(Country entity) {
		jdbcTemplate.update("delete from country where id=?", entity.getId());

	}

	@Override
	public Long insert(final Country entity) {

		final String INSERT_SQL = "insert into country (country_name) values (?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getCountryName());

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

}
