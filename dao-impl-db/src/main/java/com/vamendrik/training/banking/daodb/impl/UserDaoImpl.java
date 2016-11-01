package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vamendrik.training.banking.daodb.UserDao;
import com.vamendrik.training.banking.daodb.mapper.UserMapper;
import com.vamendrik.training.banking.datamodel.User;

public class UserDaoImpl implements UserDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> getAll() {
		List<User> users = this.jdbcTemplate.query("select * from user",new UserMapper());
		return users;
	}

	@Override
	public User getById(Long id) {
		User user = this.jdbcTemplate.queryForObject("select * from user where id=?",new Object[] {id} ,new UserMapper());
		return user;
	}

	@Override
	public void update(User entity) {
		this.jdbcTemplate.update("update user set login=?,password=?,role_id=? where id=?", entity.getLogin(),entity.getPassword(),entity.getRoleId(),entity.getId());
		
	}

	@Override
	public void delete(User entity) {
		this.jdbcTemplate.update("delete from user where id=?",entity.getId());
		
	}

	@Override
	public void insert(User entity) {
		this.jdbcTemplate.update("insert into user (login,password,role_id) values (?,?,?)",entity.getLogin(),entity.getPassword(),entity.getRoleId());
		
	}

}
