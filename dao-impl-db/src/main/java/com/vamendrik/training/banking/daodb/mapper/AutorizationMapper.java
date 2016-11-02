package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.Autorization;

public class AutorizationMapper implements RowMapper<Autorization> {

	@Override
	public Autorization mapRow(ResultSet rs, int rowNum) throws SQLException {
		Autorization autorization=new Autorization();
		autorization.setId(rs.getLong("id"));
		autorization.setLogin(rs.getString("login"));
		autorization.setPassword(rs.getString("password"));
		autorization.setRoleId(rs.getLong("role_id"));
		return autorization;
	}

}
