package com.vamendrik.training.banking.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.RoleDao;
import com.vamendrik.training.banking.daodb.mapper.RoleMapper;
import com.vamendrik.training.banking.datamodel.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from role", Long.class);
	}

	@Override
	public List<Role> getAll() {
		try {
			List<Role> roles = this.jdbcTemplate.query("select * from role", new RoleMapper());
			return roles;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public Role getById(Long id) {
		try {
			Role role = this.jdbcTemplate.queryForObject("select * from role where id=?", new Object[] { id },
					new RoleMapper());
			return role;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(Role entity) {
		this.jdbcTemplate.update("update role set role_type=? where id=?", entity.getRoleName(), entity.getId());

	}

	@Override
	public void delete(Role entity) {
		this.jdbcTemplate.update("delete from role where id=?", entity.getId());

	}

	@Override
	public Long insert(final Role entity) {

		final String INSERT_SQL = "insert into role (role_type) values (?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getRoleName());

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

}
