package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daodb.AbstractDAO;
import com.vamendrik.training.banking.daodb.mapper.RoleMapper;
import com.vamendrik.training.banking.daodb.mapper.TransactionMapper;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.Transaction;

@Repository
public class TransactionDaoImpl implements AbstractDAO<Transaction,Long> {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Transaction> getAll() {
		List<Transaction> transactions = this.jdbcTemplate.query("select * from transaction",new TransactionMapper());
		return transactions;
	}

	@Override
	public Transaction getById(Long id) {
		Transaction transaction = this.jdbcTemplate.queryForObject("select * from transaction where id=?",new Object[] {id},new TransactionMapper());
		return transaction;
	}

	@Override
	public void update(Transaction entity) {
		this.jdbcTemplate.update("update transaction set date_completion=?,status=?,sum=?,credit_card_id=? where id=?", entity.getDateCompletion(),entity.isStatus(),entity.getSum(),entity.getCreditCardId(),entity.getId());
		
	}

	@Override
	public void delete(Transaction entity) {
		this.jdbcTemplate.update("delete from transaction where id=?",entity.getId());
		
	}

	@Override
	public void insert(Transaction entity) {
		this.jdbcTemplate.update("insert into transaction (date_completion,status,sum,credit_card_id) values(?,?,?,?)",entity.getDateCompletion(),entity.isStatus(),entity.getSum(),entity.getCreditCardId());
		
	}

}
