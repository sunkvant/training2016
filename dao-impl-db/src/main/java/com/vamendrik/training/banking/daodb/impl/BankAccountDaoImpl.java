package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.BankAccountDao;
import com.vamendrik.training.banking.daodb.mapper.BankAccountMapper;
import com.vamendrik.training.banking.datamodel.BankAccount;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BankAccount> getAll() {
		List<BankAccount> bankAccount = this.jdbcTemplate.query("select * from bank_account",new BankAccountMapper());
		return bankAccount;
	}

	@Override
	public BankAccount getById(Long id) {
		BankAccount bankAccount = this.jdbcTemplate.queryForObject("select * from bank_account where id=?",new Object[] {id},new BankAccountMapper());
		return bankAccount;
	}

	@Override
	public void update(BankAccount entity) {
		this.jdbcTemplate.update("update bank_account set number_account=?,sum=?,status=? where id=?",entity.getNumberAccount(),entity.getSum(),entity.isStatus(),entity.getId());
		
	}

	@Override
	public void delete(BankAccount entity) {
		this.jdbcTemplate.update("delete from bank_account where id=?",entity.getId());
		
	}

	@Override
	public void insert(BankAccount entity) {
		jdbcTemplate.update("insert into bank_account (number_account,sum,status) values (?,?,?)",entity.getNumberAccount(),entity.getSum(),entity.isStatus());
		
	}

}
