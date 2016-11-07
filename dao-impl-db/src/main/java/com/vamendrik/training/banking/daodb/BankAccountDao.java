package com.vamendrik.training.banking.daodb;

import com.vamendrik.training.banking.datamodel.BankAccount;

public interface BankAccountDao extends AbstractDao<BankAccount,Long> {

	public BankAccount getLast();
	
}
