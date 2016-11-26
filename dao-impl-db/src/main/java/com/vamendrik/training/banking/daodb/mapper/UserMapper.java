package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User client = new User();
		client.setId(rs.getLong("id"));
		client.setFirstName(rs.getString("first_name"));
		client.setLastName(rs.getString("last_name"));
		client.setMiddleName(rs.getString("middle_name"));
		client.setNumberOfPassport(rs.getString("number_of_passport"));
		client.setDateBorn(rs.getDate("date_born"));
		client.setCityId(rs.getLong("city_id"));
		client.setLogin(rs.getString("login"));
		client.setPassword("password");
		client.setRoleId(rs.getLong("role_id"));
		client.setDelete(rs.getBoolean("is_delete"));
		return client;
	}

}
