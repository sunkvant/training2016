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

import com.vamendrik.training.banking.daodb.BankAccountDao;
import com.vamendrik.training.banking.daodb.mapper.BankAccountMapper;
import com.vamendrik.training.banking.daodb.mapper.CreditCardMapper;
import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.CreditCard;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from bank_account", Long.class);
	}

	@Override
	public BankAccount getLast() {
		try {
			BankAccount bankAccount = this.jdbcTemplate
					.queryForObject("select * from bank_account ORDER BY id DESC LIMIT 1", new BankAccountMapper());
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public List<BankAccount> getAll() {
		try {
			List<BankAccount> bankAccount = this.jdbcTemplate.query("select * from bank_account",
					new BankAccountMapper());
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public BankAccount getById(Long id) {
		try {
			BankAccount bankAccount = this.jdbcTemplate.queryForObject("select * from bank_account where id=?",
					new Object[] { id }, new BankAccountMapper());
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(BankAccount entity) {
		this.jdbcTemplate.update("update bank_account set number_account=?,sum=?,status=?,client_id=? where id=?",
				entity.getNumberAccount(), entity.getSum(), entity.isStatus(), entity.getClientId(), entity.getId());

	}

	@Override
	public void delete(BankAccount entity) {
		this.jdbcTemplate.update("delete from bank_account where id=?", entity.getId());

	}

	@Override
	public Long insert(final BankAccount entity) {

		final String INSERT_SQL = "insert into bank_account (number_account,sum,status,client_id) values (?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setLong(1, entity.getNumberAccount());
				ps.setDouble(2, entity.getSum());
				ps.setBoolean(3, entity.isStatus());
				ps.setLong(4, entity.getClientId());

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();

	}

}
