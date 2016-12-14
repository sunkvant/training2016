package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.BankAccount;

public class BankAccountMapper implements RowMapper<BankAccount> {

	@Override
	public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		BankAccount bankAccount=new BankAccount();
		bankAccount.setId(rs.getLong("id"));
		bankAccount.setNumberAccount(rs.getLong("number_account"));
		bankAccount.setSum(rs.getDouble("sum"));
		bankAccount.setStatus(rs.getBoolean("status"));
		bankAccount.setUserId(rs.getLong("user_id"));
		bankAccount.setDelete(rs.getBoolean("is_delete"));
		return bankAccount;
	}

}
