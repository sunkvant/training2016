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

import com.vamendrik.training.banking.daoapi.ITransactionDao;
import com.vamendrik.training.banking.daodb.mapper.TransactionMapper;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.Transaction;

@Repository
public class TransactionDaoImpl implements ITransactionDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Transaction> getAll() {
		try {
			List<Transaction> transactions = this.jdbcTemplate.query("select * from transaction",
					new TransactionMapper());
			return transactions;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<Transaction>(0);

		}
	}

	@Override
	public Transaction getById(Long id) {
		try {
			Transaction transaction = this.jdbcTemplate.queryForObject("select * from transaction where id=?",
					new Object[] { id }, new TransactionMapper());
			return transaction;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(Transaction entity) {
		this.jdbcTemplate.update(
				"update transaction set date_completion=?,status=?,sum=?,from_to=?,credit_card_id=? where id=?",
				entity.getDateCompletion(), entity.isStatus(), entity.getSum(), entity.getFromTo(),
				entity.getCreditCardId(), entity.getId());

	}

	@Override
	public void delete(Transaction entity) {
		this.jdbcTemplate.update("delete from transaction where id=?", entity.getId());

	}

	@Override
	public Long insert(final Transaction entity) {

		final String INSERT_SQL = "insert into transaction (date_completion,status,sum,from_to,credit_card_id,is_delete) values(?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setTimestamp(1, entity.getDateCompletion());
				ps.setBoolean(2, entity.isStatus());
				ps.setDouble(3, entity.getSum());
				ps.setLong(4, entity.getFromTo());
				ps.setLong(5, entity.getCreditCardId());
				ps.setBoolean(6, false);
				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public List<Transaction> getAllByCreditCardId(Long creditCardId) {
		try {
			List<Transaction> transactions = this.jdbcTemplate.query(
					"SELECT public.transaction.* FROM public.transaction  join public.credit_card on public.transaction.credit_card_id=public.credit_card.id where public.credit_card.id=?",
					new Object[] { creditCardId }, new TransactionMapper());
			return transactions;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<Transaction>(0);

		}
	}

	@Override
	public List<Transaction> getAllByBankAccountId(Long bankAccountId) {
		try {
			List<Transaction> transactions = this.jdbcTemplate.query(
					"SELECT public.transaction.* FROM public.transaction join public.credit_card on public.transaction.credit_card_id=public.credit_card.id join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id where public.bank_account.id=?",
					new Object[] { bankAccountId }, new TransactionMapper());
			return transactions;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<Transaction>(0);

		}
	}

	@Override
	public List<Transaction> getAllByUserId(Long userId) {
		try {
			List<Transaction> transactions = this.jdbcTemplate.query(
					"SELECT public.transaction.* FROM public.transaction join public.credit_card on public.transaction.credit_card_id=public.credit_card.id join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id join public.\"user\" on public.bank_account.user_id=public.\"user\".id where public.\"user\".id=?",
					new Object[] { userId }, new TransactionMapper());
			return transactions;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<Transaction>(0);

		}
	}

}
