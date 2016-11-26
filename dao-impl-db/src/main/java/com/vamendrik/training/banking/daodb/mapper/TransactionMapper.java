package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.Transaction;

public class TransactionMapper implements RowMapper<Transaction> {

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaction transaction=new Transaction();
		transaction.setId(rs.getLong("id"));
		transaction.setDateCompletion(rs.getTimestamp("date_completion"));
		transaction.setStatus(rs.getBoolean("status"));
		transaction.setSum(rs.getDouble("sum"));
		transaction.setFromTo(rs.getLong("from_to"));
		transaction.setCreditCardId(rs.getLong("credit_card_id"));
		transaction.setDelete(rs.getBoolean("is_delete"));
		return transaction;
	}

}
