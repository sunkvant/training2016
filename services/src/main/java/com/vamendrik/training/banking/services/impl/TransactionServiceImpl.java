package com.vamendrik.training.banking.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamendrik.training.banking.daoapi.IBankAccountDao;
import com.vamendrik.training.banking.daoapi.ITransactionDao;
import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.CreditCard;
import com.vamendrik.training.banking.datamodel.Transaction;
import com.vamendrik.training.banking.services.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Inject
	ITransactionDao transactionDao;
	
	@Inject
	IBankAccountDao bankAccountDao;
	
	@Transactional
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
	
	@Transactional
	public void transferToBankAccount(CreditCard source,CreditCard destination,double sum) {
		
		BankAccount bankAccountSource=bankAccountDao.getById(source.getBankAccountId());
		BankAccount bankAccountDest=bankAccountDao.getById(destination.getBankAccountId());
		
		bankAccountSource.setSum(bankAccountSource.getSum()-sum);
		bankAccountDest.setSum(bankAccountDest.getSum()+sum);
		
		bankAccountDao.update(bankAccountSource);
		bankAccountDao.update(bankAccountDest);
		
		createTransaction(source,destination,sum);
		
		
	}

	@Override
	public List<Long> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Long entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long get(Transaction id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllByCreditCardId(Long creditCardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllByBankAccountId(Long bankAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
