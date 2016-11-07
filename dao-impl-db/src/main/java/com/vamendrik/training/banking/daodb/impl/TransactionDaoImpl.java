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

import com.vamendrik.training.banking.daodb.TransactionDao;
import com.vamendrik.training.banking.daodb.mapper.TransactionMapper;
import com.vamendrik.training.banking.datamodel.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from transaction", Long.class);
	}

	@Override
	public List<Transaction> getAll() {
		try {
			List<Transaction> transactions = this.jdbcTemplate.query("select * from transaction",
					new TransactionMapper());
			return transactions;
		} catch (EmptyResultDataAccessException e) {

			return null;

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

		final String INSERT_SQL = "insert into transaction (date_completion,status,sum,from_to,credit_card_id) values(?,?,?,?,?)";

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
				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

}
