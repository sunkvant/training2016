package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.CreditCard;
import com.vamendrik.training.banking.datamodel.Transaction;

public interface ITransactionService extends IAbstractService<Long,Transaction> {
	
	public List<Transaction> getAllByCreditCardId(Long creditCardId);
	public List<Transaction> getAllByBankAccountId(Long bankAccountId);
	public List<Transaction> getAllByUserId(Long userId);
	public void transferToBankAccount(CreditCard source,CreditCard destination,double sum);

}
