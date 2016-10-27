package com.vamendrik.training.banking.daodb.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.AbstractDAO;
import com.vamendrik.training.banking.datamodel.Client;

@Repository
public class ClientDaoImpl implements AbstractDAO<Client,Long> {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Client> getAll() {
		List<Client> clients = this.jdbcTemplate.query("select * from client", new RowMapper<Client>() {

			@Override
			public Client mapRow(ResultSet arg0, int arg1) throws SQLException {
				Client client = new Client();
				client.setId(arg0.getLong("id"));
				client.setFirstName(arg0.getString("first_name"));
				client.setLastName(arg0.getString("last_name"));
				client.setMiddleName(arg0.getString("middle_name"));
				client.setNumberOfPassport(arg0.getString("number_of_passport"));
				client.setDateBorn(arg0.getDate("date_born"));
				client.setCityId(arg0.getLong("city_id"));
				return client;
			}
		});
		return clients;
	}

	@Override
	public Client getById(Long id) {
		Client client = jdbcTemplate.queryForObject("select * from client where id=?", new Object[] {id}, new RowMapper<Client>() {

			@Override
			public Client mapRow(ResultSet arg0, int arg1) throws SQLException {
				Client client = new Client();
				client.setId(arg0.getLong("id"));
				client.setFirstName(arg0.getString("first_name"));
				client.setLastName(arg0.getString("last_name"));
				client.setMiddleName(arg0.getString("middle_name"));
				client.setNumberOfPassport(arg0.getString("number_of_passport"));
				client.setDateBorn(arg0.getDate("date_born"));
				client.setCityId(arg0.getLong("city_id"));
				return client;
			}
		});
		return client;
	}

	@Override
	public void update(Client entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Client entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
