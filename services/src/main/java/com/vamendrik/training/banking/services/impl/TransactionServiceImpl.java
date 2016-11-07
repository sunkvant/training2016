package com.vamendrik.training.banking.services.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.BankAccountDao;
import com.vamendrik.training.banking.daodb.TransactionDao;
import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.CreditCard;
import com.vamendrik.training.banking.datamodel.Transaction;

@Service
public class TransactionServiceImpl {
	
	@Inject
	TransactionDao transactionDao;
	
	@Inject
	BankAccountDao bankAccountDao;
	
	private void createTransaction(CreditCard source,CreditCard destination,double sum) {
		
		
		Transaction transaction=new Transaction();
		
		transaction.setDateCompletion(new Timestamp(new Date().getTime()));
		transaction.setStatus(true);
		transaction.setSum(-sum);
		transaction.setFromTo(destination.getId());
		transaction.setCreditCardId(source.getId());
		
		transactionDao.insert(transaction);
		
		transaction.setDateCompletion(new Timestamp(new Date().getTime()));
		transaction.setStatus(true);
		transaction.setSum(sum);
		transaction.setFromTo(source.getId());
		transaction.setCreditCardId(destination.getId());
		
		transactionDao.insert(transaction);
	}
	
	public void TransferToBankAccount(CreditCard source,CreditCard destination,double sum) {
		
		BankAccount bankAccountSource=bankAccountDao.getById(source.getBankAccountId());
		BankAccount bankAccountDest=bankAccountDao.getById(destination.getBankAccountId());
		
		bankAccountSource.setSum(bankAccountSource.getSum()-sum);
		bankAccountDest.setSum(bankAccountDest.getSum()+sum);
		
		bankAccountDao.update(bankAccountSource);
		bankAccountDao.update(bankAccountDest);
		
		createTransaction(source,destination,sum);
		
		
	}
	

}
