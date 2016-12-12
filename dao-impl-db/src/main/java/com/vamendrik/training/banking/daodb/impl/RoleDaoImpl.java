package com.vamendrik.training.banking.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.IRoleDao;
import com.vamendrik.training.banking.daodb.mapper.RoleMapper;
import com.vamendrik.training.banking.datamodel.CreditCard;
import com.vamendrik.training.banking.datamodel.Role;

@Repository
public class RoleDaoImpl implements IRoleDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Role> getAll() {
		try {
			List<Role> roles = this.jdbcTemplate.query("select * from role", new RoleMapper());
			return roles;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<Role>(0);

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
		this.jdbcTemplate.update("update role set role_type=?,is_delete=? where id=?", entity.getRoleName(), entity.isDelete(), entity.getId());

	}

	@Override
	public void delete(Role entity) {
		this.jdbcTemplate.update("update role set is_delete=true where id=?", entity.getId());

	}

	@Override
	public Long insert(final Role entity) {

		final String INSERT_SQL = "insert into role (role_type,is_delete) values (?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getRoleName());
				ps.setBoolean(2, entity.isDelete());
				
				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public Role getByRoleType(String roleType) {
		try {
			Role role = this.jdbcTemplate.queryForObject("select * from role where role_type=?", new Object[] { roleType },
					new RoleMapper());
			return role;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public List<Role> getAllByUserId(Long userId) {
		try {
			List<Role> roles = this.jdbcTemplate.query("SELECT public.role.* FROM public.role join public.user_to_role on  public.user_to_role.role_id=public.role.id join public.\"user\" on public.user_to_role.user_id= public.\"user\".id where public.\"user\".id=?", new Object[] {userId},new RoleMapper());

			return roles;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<Role>(0);

		}
	}

}
