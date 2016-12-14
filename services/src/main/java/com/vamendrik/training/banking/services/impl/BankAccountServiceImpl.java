package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daoapi.IBankAccountDao;
import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.services.IBankAccountService;

@Service
public class BankAccountServiceImpl implements IBankAccountService {
	
	@Inject
	IBankAccountDao bankAccountDao;
	
	
	private Long generateNumberAccount() {
		
		
		if (bankAccountDao.getLast()==null) {
			
			return 368591075l;
		
		} else {
			
			return bankAccountDao.getLast().getNumberAccount()+new Date().getSeconds()+1;
			
		}
		
		
	}
	
	@Override
	public List<BankAccount> getAll() {
		
		return bankAccountDao.getAll();
		
		
	}
	
	@Override
	public void createBankAccount(double sum,boolean status,Long clientId) {
		
		BankAccount bankAccount=new BankAccount();
		
		bankAccount.setSum(sum);
		bankAccount.setStatus(status);
		bankAccount.setUserId(clientId);
		bankAccount.setNumberAccount(generateNumberAccount());
		
		bankAccountDao.insert(bankAccount);
		
		
	}
	
	@Override
	public void delete(BankAccount bankAccount) {
		
		
		bankAccountDao.delete(bankAccount);
		
	}
	
	
	@Override
	public void save(BankAccount bankAccount) {
		
		if (bankAccountDao.getById(bankAccount.getId())==null) {
			
			bankAccountDao.insert(bankAccount);
			
			
		} else {
			
			
			bankAccountDao.update(bankAccount);
			
		}
		
		
	}
	
	@Override
	public BankAccount get(Long id) {
		
		return bankAccountDao.getById(id);
		
	}

	@Override
	public List<BankAccount> getAllByUserId(Long userId) {

		return bankAccountDao.getAllByUserId(userId);
	}

	@Override
	public BankAccount getByNumberAccount(Long numberAccount) {

		return bankAccountDao.getByNumberAccount(numberAccount);
	}

	@Override
	public BankAccount getLast() {
		
		return bankAccountDao.getLast();
		
	}
	

}
