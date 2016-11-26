package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.BankAccount;

public interface IBankAccountDao extends IAbstractDao<BankAccount,Long> {

	public List<BankAccount> getAllByUserId(Long userId);
	public BankAccount getByNumberAccount(Long numberAccount);
	public BankAccount getLast();
	
}
