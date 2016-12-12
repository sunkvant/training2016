package com.vamendrik.training.banking.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.IUserDao;
import com.vamendrik.training.banking.daodb.mapper.RoleMapper;
import com.vamendrik.training.banking.daodb.mapper.UserMapper;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAll() {
		try {
			List<User> clients = this.jdbcTemplate.query("select * from \"user\"", new UserMapper());
			return clients;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<User>(0);

		}
	}

	@Override
	public User getById(Long id) {
		try {
			User client = jdbcTemplate.queryForObject("select * from \"user\" where id=?", new Object[] { id },
					new UserMapper());
			return client;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(User entity) {
		jdbcTemplate.update(
				"update \"user\" set first_name=?,last_name=?,middle_name=?,number_of_passport=?,date_born=?,login=?,password=?,city_id=? where id=?",
				entity.getFirstName(), entity.getLastName(), entity.getMiddleName(), entity.getNumberOfPassport(),
				entity.getDateBorn(), entity.getLogin(), entity.getPassword(), entity.getCityId(),
				entity.getId());
	}

	@Override
	public void delete(User entity) {
		jdbcTemplate.update("update \"user\" set is_delete=true where id=?", entity.getId());
	}

	@Override
	public Long insert(final User entity) {

		final String INSERT_SQL = "insert into client (first_name,last_name,middle_name,number_of_passport,date_born,login,password,city_id,is_delete) values (?,?,?,?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getFirstName());
				ps.setString(2, entity.getLastName());
				ps.setString(3, entity.getMiddleName());
				ps.setString(4, entity.getNumberOfPassport());
				ps.setDate(5, new java.sql.Date(entity.getDateBorn().getTime()));
				ps.setString(6, entity.getLogin());
				ps.setString(7, entity.getPassword());
				ps.setLong(8, entity.getCityId());
				ps.setBoolean(9, false);
				return ps;
			}

		}, keyHolder);
		
		for(int i=0; i<entity.getRoles().size(); i++) {
			
			jdbcTemplate.update("insert into user_to_role (user_id,role_id) values (?,?)", keyHolder.getKey().longValue(),entity.getRoles().get(i).getId());
			
			
		}

		return keyHolder.getKey().longValue();

	}

	@Override
	public List<User> getAllByRoleId(Long roleId) {
		try {
			List<User> clients = this.jdbcTemplate.query(
					"SELECT public.\"user\".* FROM public.\"user\" join public.user_to_role on  public.\"user\".id=public.user_to_role.user_id join public.role on public.user_to_role.role_id=public.role.id where public.role.id=?",
					 new Object[] { roleId }, new UserMapper());
			return clients;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<User>(0);

		}
	}

	@Override
	public List<User> getAllByCityId(Long cityId) {
		try {
			List<User> clients = this.jdbcTemplate.query(
					"SELECT public.\"user\".* FROM public.\"user\" join public.city on public.\"user\".city_id=public.city.id where public.city.id=?",
					 new Object[] { cityId }, new UserMapper());
			return clients;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<User>(0);

		}
	}

	@Override
	public List<User> getAllByCountryId(Long countryId) {
		try {
			List<User> clients = this.jdbcTemplate.query(
					"SELECT public.\"user\".* FROM public.\"user\" join public.city on public.\"user\".city_id=public.city.id join public.country on public.city.country_id=public.country.id where public.country.id=?",
					 new Object[] { countryId }, new UserMapper());
			return clients;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<User>(0);

		}
	}

	@Override
	public User getByNumberPassport(String numberOfPassport) {
		try {
			User client = jdbcTemplate.queryForObject("select * from \"user\" where number_of_passport=?", new Object[] { numberOfPassport },
					new UserMapper());
			return client;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public User getByLogin(String login) {
		try {
			User client = jdbcTemplate.queryForObject("select * from \"user\" where login=?", new Object[] { login },
					new UserMapper());
			return client;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

}
