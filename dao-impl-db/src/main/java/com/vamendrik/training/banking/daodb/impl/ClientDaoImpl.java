package com.vamendrik.training.banking.daodb.impl;

import java.sql.Connection;
import java.sql.Date;
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

import com.vamendrik.training.banking.daodb.ClientDao;
import com.vamendrik.training.banking.daodb.mapper.ClientMapper;
import com.vamendrik.training.banking.datamodel.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from client", Long.class);
	}

	@Override
	public List<Client> getAll() {
		try {
			List<Client> clients = this.jdbcTemplate.query("select * from client", new ClientMapper());
			return clients;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public Client getById(Long id) {
		try {
			Client client = jdbcTemplate.queryForObject("select * from client where id=?", new Object[] { id },
					new ClientMapper());
			return client;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(Client entity) {
		jdbcTemplate.update(
				"update client set first_name=?,last_name=?,middle_name=?,number_of_passport=?,date_born=?,city_id=? where id=?",
				entity.getFirstName(), entity.getLastName(), entity.getMiddleName(), entity.getNumberOfPassport(),
				entity.getDateBorn(), entity.getCityId(), entity.getId());
	}

	@Override
	public void delete(Client entity) {
		jdbcTemplate.update("delete from client where id=?", entity.getId());
	}

	@Override
	public Long insert(final Client entity) {

		final String INSERT_SQL = "insert into client (first_name,last_name,middle_name,number_of_passport,date_born,city_id) values (?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getFirstName());
				ps.setString(2, entity.getLastName());
				ps.setString(3, entity.getMiddleName());
				ps.setString(4, entity.getNumberOfPassport());
				ps.setDate(5, new java.sql.Date(entity.getDateBorn().getTime()));
				ps.setLong(6, entity.getCityId());
				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();

	}

}
