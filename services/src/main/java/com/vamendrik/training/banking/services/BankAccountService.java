package com.vamendrik.training.banking.services;

import com.vamendrik.training.banking.datamodel.BankAccount;

public interface BankAccountService extends AbstractService<BankAccount,Long> {
	
	public void add(double sum,boolean status,Long clientId);

}
