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

import com.vamendrik.training.banking.daodb.AutorizationDao;
import com.vamendrik.training.banking.daodb.mapper.AutorizationMapper;
import com.vamendrik.training.banking.datamodel.Autorization;

@Repository
public class AutorizationDaoImpl implements AutorizationDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from autorization", Long.class);
	}

	@Override
	public List<Autorization> getAll() {
		try {
			List<Autorization> users = this.jdbcTemplate.query("select * from autorization", new AutorizationMapper());
			return users;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public Autorization getById(Long id) {
		try {
			Autorization autorization = this.jdbcTemplate.queryForObject("select * from autorization where id=?",
					new Object[] { id }, new AutorizationMapper());
			return autorization;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(Autorization entity) {
		this.jdbcTemplate.update("update autorization set login=?,password=?,role_id=? where id=?", entity.getLogin(),
				entity.getPassword(), entity.getRoleId(), entity.getId());

	}

	@Override
	public void delete(Autorization entity) {
		this.jdbcTemplate.update("delete from autorization where id=?", entity.getId());

	}

	@Override
	public Long insert(final Autorization entity) {

		final String INSERT_SQL = "insert into autorization (login,password,role_id) values (?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getLogin());
				ps.setString(2, entity.getPassword());
				ps.setLong(3, entity.getRoleId());

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

}
