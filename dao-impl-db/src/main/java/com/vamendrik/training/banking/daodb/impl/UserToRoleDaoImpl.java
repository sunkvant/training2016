package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vamendrik.training.banking.daoapi.IUserToRole;
import com.vamendrik.training.banking.datamodel.Role;

public class UserToRoleDaoImpl implements IUserToRole<Long> {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void setRolesUser(Long userId, List<Role> roles) {
		
		for(int i=0; i<roles.size(); i++) {
			
			jdbcTemplate.update("insert into user_to_role (user_id,role_id) values (?,?)", userId,roles.get(i).getId());
			
			
		}
		
	}
	
	public void deleteRolesUser(Long userId) {
		
		
		jdbcTemplate.update("delete from user_to_role where user_id=?", userId);
		
		
	}

}
