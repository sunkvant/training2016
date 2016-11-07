package com.vamendrik.training.banking.daodb.impl;

import java.sql.Connection;
import java.sql.Date;
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

import com.vamendrik.training.banking.daodb.CreditCardDao;
import com.vamendrik.training.banking.daodb.mapper.CreditCardMapper;
import com.vamendrik.training.banking.datamodel.CreditCard;

@Repository
public class CreditCardDaoImpl implements CreditCardDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from credit_card", Long.class);
	}

	@Override
	public CreditCard getLast() {
		try {
			CreditCard creditCard = this.jdbcTemplate
					.queryForObject("select * from credit_card ORDER BY id DESC LIMIT 1", new CreditCardMapper());
			return creditCard;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public List<CreditCard> getAll() {
		try {
			List<CreditCard> creditCard = this.jdbcTemplate.query("select * from credit_card", new CreditCardMapper());
			return creditCard;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public CreditCard getById(Long id) {
		try {
			CreditCard creditCard = this.jdbcTemplate.queryForObject("select * from credit_card where id=?",
					new Object[] { id }, new CreditCardMapper());
			return creditCard;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}

	}

	@Override
	public void update(CreditCard entity) {
		this.jdbcTemplate.update(
				"update credit_card set number_card=?,validity=?,status=?,bank_account_id=? where id=?",
				entity.getNumberCard(), entity.getValidity(), entity.isStatus(), entity.getBankAccountId(),
				entity.getId());

	}

	@Override
	public void delete(CreditCard entity) {
		this.jdbcTemplate.update("delete from credit_card where id=?", entity.getId());

	}

	@Override
	public Long insert(final CreditCard entity) {

		final String INSERT_SQL = "insert into credit_card (number_card,validity,status,bank_account_id) values (?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setLong(1, entity.getNumberCard());
				ps.setDate(2, new java.sql.Date(entity.getValidity().getTime()));
				ps.setBoolean(3, entity.isStatus());
				ps.setLong(4, entity.getBankAccountId());

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

}
