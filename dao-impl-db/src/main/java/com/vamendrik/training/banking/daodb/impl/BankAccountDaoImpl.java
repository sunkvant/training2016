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

import com.vamendrik.training.banking.daoapi.IBankAccountDao;
import com.vamendrik.training.banking.daodb.mapper.BankAccountMapper;
import com.vamendrik.training.banking.datamodel.BankAccount;

@Repository
public class BankAccountDaoImpl implements IBankAccountDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public BankAccount getLast() {
		try {
			BankAccount bankAccount = this.jdbcTemplate
					.queryForObject("select * from bank_account where is_delete=false ORDER BY id DESC LIMIT 1", new BankAccountMapper());
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public List<BankAccount> getAll() {
		try {
			List<BankAccount> bankAccount = this.jdbcTemplate.query("select * from bank_account where is_delete=false",
					new BankAccountMapper());
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<BankAccount>(0);

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
		this.jdbcTemplate.update(
				"update bank_account set number_account=?,sum=?,status=?,client_id=?,isDelete=? where id=?",
				entity.getNumberAccount(), entity.getSum(), entity.isStatus(), entity.getClientId(), entity.isDelete(),
				entity.getId());

	}

	@Override
	public void delete(BankAccount entity) {
		this.jdbcTemplate.update("update bank_account set is_delete=true where id=?", entity.getId());

	}

	@Override
	public Long insert(final BankAccount entity) {

		final String INSERT_SQL = "insert into bank_account (number_account,sum,status,user_id,is_delete) values (?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setLong(1, entity.getNumberAccount());
				ps.setDouble(2, entity.getSum());
				ps.setBoolean(3, entity.isStatus());
				ps.setLong(4, entity.getClientId());
				ps.setBoolean(5, false);

				return ps;
			}

		}, keyHolder);

		return keyHolder.getKey().longValue();

	}

	@Override
	public List<BankAccount> getAllByUserId(Long userId) {
		try {
			List<BankAccount> bankAccount = this.jdbcTemplate.query(
					"SELECT public.bank_account.* FROM public.bank_account join public.\"user\" on public.bank_account.user_id=public.\"user\".id where public.\"user\".id=? and public.\"user\".is_delete=false and public.bank_account.is_delete=false",
					new Object[] { userId }, new BankAccountMapper());
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<BankAccount>(0);

		}
	}

	@Override
	public BankAccount getByNumberAccount(Long numberAccount) {
		try {
			BankAccount bankAccount = this.jdbcTemplate.queryForObject("select * from bank_account where number_account=?",
					new Object[] { numberAccount }, new BankAccountMapper());
			return bankAccount;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

}
