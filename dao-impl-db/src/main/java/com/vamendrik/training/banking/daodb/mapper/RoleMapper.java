package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.Role;

public class RoleMapper implements RowMapper<Role>  {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role=new Role();
		role.setId(rs.getLong("id"));
		role.setRoleName(rs.getString("role_type"));
		role.setDelete(rs.getBoolean("is_delete"));
		return role;
	}

}
