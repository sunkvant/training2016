package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Transaction;

public interface ITransactionDao extends IAbstractDao<Transaction,Long> {
	
	public List<Transaction> getAllByCreditCardId(Long creditCardId);
	public List<Transaction> getAllByBankAccountId(Long bankAccountId);
	public List<Transaction> getAllByUserId(Long userId);
	
}
