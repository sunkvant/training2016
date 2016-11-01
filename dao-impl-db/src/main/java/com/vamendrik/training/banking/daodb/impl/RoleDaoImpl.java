package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.RoleDao;
import com.vamendrik.training.banking.daodb.mapper.RoleMapper;
import com.vamendrik.training.banking.datamodel.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Role> getAll() {
		List<Role> roles = this.jdbcTemplate.query("select * from role",new RoleMapper());
		return roles;
	}

	@Override
	public Role getById(Long id) {
		Role role = this.jdbcTemplate.queryForObject("select * from role where id=?",new Object[] {id},new RoleMapper());
		return role;
	}

	@Override
	public void update(Role entity) {
		this.jdbcTemplate.update("update role set role_type=? where id=?", entity.getRoleName(),entity.getId());
		
	}

	@Override
	public void delete(Role entity) {
		this.jdbcTemplate.update("delete from role where id=?",entity.getId());
		
	}

	@Override
	public void insert(Role entity) {
		this.jdbcTemplate.update("insert into role (role_type) values (?)",entity.getRoleName());
		
	}

}
