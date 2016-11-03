package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.ClientDao;
import com.vamendrik.training.banking.daodb.mapper.ClientMapper;
import com.vamendrik.training.banking.datamodel.Client;

@Repository
public class ClientDaoImpl implements ClientDao {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Client> getAll() {
		List<Client> clients = this.jdbcTemplate.query("select * from client",new ClientMapper());
		return clients;
	}

	@Override
	public Client getById(Long id) {
		Client client = jdbcTemplate.queryForObject("select * from client where id=?", new Object[] {id},new ClientMapper());
		return client;
	}

	@Override
	public void update(Client entity) {
		jdbcTemplate.update("update client set first_name=?,last_name=?,middle_name=?,number_of_passport=?,date_born=?,city_id=? where id=?",
				entity.getFirstName(),entity.getLastName(),entity.getMiddleName(),entity.getNumberOfPassport(),entity.getDateBorn(),entity.getCityId(),entity.getId());
	}

	@Override
	public void delete(Client entity) {
		jdbcTemplate.update("delete from client where id=?",entity.getId());
	}

	@Override
	public void insert(Client entity) {
		jdbcTemplate.update("insert into client (first_name,last_name,middle_name,number_of_passport,date_born,city_id) values (?,?,?,?,?,?)",entity.getFirstName(),entity.getLastName(),entity.getMiddleName(),entity.getNumberOfPassport(),entity.getDateBorn(),entity.getCityId());
	}

}
