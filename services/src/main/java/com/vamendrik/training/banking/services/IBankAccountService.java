package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.BankAccount;

public interface IBankAccountService extends IAbstractService<BankAccount,Long> {
	
	public List<BankAccount> getAllByUserId(Long userId);
	public BankAccount getByNumberAccount(Long numberAccount);
	public BankAccount getLast();
	public void createBankAccount(double sum,boolean status,Long clientId);

}
