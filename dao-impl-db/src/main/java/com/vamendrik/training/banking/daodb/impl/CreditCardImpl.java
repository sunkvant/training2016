package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vamendrik.training.banking.daodb.AbstractDAO;
import com.vamendrik.training.banking.daodb.mapper.BankAccountMapper;
import com.vamendrik.training.banking.daodb.mapper.CreditCardMapper;
import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.CreditCard;

public class CreditCardImpl implements AbstractDAO<CreditCard,Long> {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CreditCard> getAll() {
		List<CreditCard> creditCard = this.jdbcTemplate.query("select * from credit_card",new CreditCardMapper());
		return creditCard;
	}

	@Override
	public CreditCard getById(Long id) {
		CreditCard creditCard = this.jdbcTemplate.queryForObject("select * from credit_card where id=?",new Object [] {id},new CreditCardMapper());
		return creditCard;
	}

	@Override
	public void update(CreditCard entity) {
		this.jdbcTemplate.update("update credit_card set bank_account_id=?,client_id=?,number_card=?,validity=?,status=? where id=?",entity.getBankAccountId(),entity.getClientId(),entity.getNumberCard(),entity.getValidity(),entity.isStatus(),entity.getId());
		
	}

	@Override
	public void deleteById(CreditCard entity) {
		this.jdbcTemplate.update("delete from credit_card where id=?",entity.getId());
		
	}

	@Override
	public void insert(CreditCard entity) {
		jdbcTemplate.update("insert into credit_card (bank_account_id,client_id,number_card,validity,status) values (?,?,?,?,?)",entity.getBankAccountId(),entity.getClientId(),entity.getNumberCard(),entity.getValidity(),entity.isStatus());
		
	}
	
	

}
