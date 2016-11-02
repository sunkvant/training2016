package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.Client;

public class ClientMapper implements RowMapper<Client> {

	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Client client = new Client();
		client.setId(rs.getLong("id"));
		client.setFirstName(rs.getString("first_name"));
		client.setLastName(rs.getString("last_name"));
		client.setMiddleName(rs.getString("middle_name"));
		client.setNumberOfPassport(rs.getString("number_of_passport"));
		client.setDateBorn(rs.getDate("date_born"));
		client.setCityId(rs.getLong("city_id"));
		client.setBankAccountId(rs.getLong("bank_account_id"));
		return client;
	}

}
