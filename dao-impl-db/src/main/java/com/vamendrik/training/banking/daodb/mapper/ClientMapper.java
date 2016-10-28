package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.Client;

public class ClientMapper implements RowMapper<Client> {

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

}
