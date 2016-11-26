package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.CreditCard;

public class CreditCardMapper implements RowMapper<CreditCard> {

	@Override
	public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
		CreditCard creditCard=new CreditCard();
		creditCard.setId(rs.getLong("id"));
		creditCard.setBankAccountId(rs.getLong("bank_account_id"));
		creditCard.setNumberCard(rs.getLong("number_card"));
		creditCard.setValidity(rs.getDate("validity"));
		creditCard.setStatus(rs.getBoolean("status"));
		creditCard.setDelete(rs.getBoolean("is_delete"));
		return creditCard;
	}
	
	
	
	
	

}
