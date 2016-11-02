package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.AutorizationDao;
import com.vamendrik.training.banking.daodb.mapper.AutorizationMapper;
import com.vamendrik.training.banking.datamodel.Autorization;

@Repository
public class AutorizationDaoImpl implements AutorizationDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Autorization> getAll() {
		List<Autorization> users = this.jdbcTemplate.query("select * from autorization",new AutorizationMapper());
		return users;
	}

	@Override
	public Autorization getById(Long id) {
		Autorization user = this.jdbcTemplate.queryForObject("select * from autorization where id=?",new Object[] {id} ,new AutorizationMapper());
		return user;
	}

	@Override
	public void update(Autorization entity) {
		this.jdbcTemplate.update("update autorization set login=?,password=?,role_id=? where id=?", entity.getLogin(),entity.getPassword(),entity.getRoleId(),entity.getId());
		
	}

	@Override
	public void delete(Autorization entity) {
		this.jdbcTemplate.update("delete from autorization where id=?",entity.getId());
		
	}

	@Override
	public void insert(Autorization entity) {
		this.jdbcTemplate.update("insert into autorization (login,password,role_id) values (?,?,?)",entity.getLogin(),entity.getPassword(),entity.getRoleId());
		
	}

}
