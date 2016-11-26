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

import com.vamendrik.training.banking.daoapi.ICreditCardDao;
import com.vamendrik.training.banking.daodb.mapper.CreditCardMapper;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.datamodel.CreditCard;

@Repository
public class CreditCardDaoImpl implements ICreditCardDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

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

			return new ArrayList<CreditCard>(0);

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
				"update credit_card set number_card=?,validity=?,status=?,bank_account_id=?,is_delete=? where id=?",
				entity.getNumberCard(), entity.getValidity(), entity.isStatus(), entity.getBankAccountId(),
				entity.isDelete(), entity.getId());

	}

	@Override
	public void delete(CreditCard entity) {
		this.jdbcTemplate.update("update credit_card set is_delete=true where id=?", entity.getId());

	}

	@Override
	public Long insert(final CreditCard entity) {

		final String INSERT_SQL = "insert into credit_card (number_card,validity,status,bank_account_id,is_delete) values (?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setLong(1, entity.getNumberCard());
				ps.setDate(2, new java.sql.Date(entity.getValidity().getTime()));
				ps.setBoolean(3, entity.isStatus());
				ps.setLong(4, entity.getBankAccountId());
				ps.setBoolean(5, false);

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public List<CreditCard> getAllByBankAccountId(Long bankAccountId) {
		try {
			List<CreditCard> creditCard = this.jdbcTemplate.query(
					"SELECT public.credit_card.* from public.credit_card join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id where public.bank_account.id=?",
					new Object[] { bankAccountId }, new CreditCardMapper());
			return creditCard;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<CreditCard>(0);

		}
	}

	@Override
	public CreditCard getByNumberCard(Long numberCard) {
		try {
			CreditCard creditCard = this.jdbcTemplate.queryForObject("select * from credit_card where number_card=?",
					new Object[] { numberCard }, new CreditCardMapper());
			return creditCard;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public List<CreditCard> getAllByUserId(Long userId) {
		try {
			List<CreditCard> creditCard = this.jdbcTemplate.query(
					"select public.credit_card.* from public.credit_card join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id join public.\"user\" on public.bank_account.user_id=public.\"user\".id where public.\"user\".id=?",
					new Object[] { userId }, new CreditCardMapper());
			return creditCard;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<CreditCard>(0);

		}
	}
}
