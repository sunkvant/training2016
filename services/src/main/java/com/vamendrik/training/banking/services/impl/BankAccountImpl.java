package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import com.vamendrik.training.banking.daodb.BankAccountDao;
import com.vamendrik.training.banking.datamodel.BankAccount;

public class BankAccountImpl {
	
	@Inject
	BankAccountDao bankAccountDao;
	
	
	public Long generateNumberAccount() {
		
		
		return null;
		
		
	}
	
	public List<BankAccount> getAll() {
		
		return bankAccountDao.getAll();
		
		
	}
	
	public void add(double sum,boolean status,Long clientId) {
		
		BankAccount bankAccount=new BankAccount();
		
		bankAccount.setSum(sum);
		bankAccount.setStatus(status);
		bankAccount.setClientId(clientId);
		bankAccount.setNumberAccount(generateNumberAccount());
		
		
	}
	
	public void delete(BankAccount bankAccount) {
		
		
		bankAccountDao.delete(bankAccount);
		
	}
	
	
	public void save(BankAccount bankAccount) {
		
		if (bankAccountDao.getById(bankAccount.getId())==null) {
			
			bankAccountDao.insert(bankAccount);
			
			
		} else {
			
			
			bankAccountDao.update(bankAccount);
			
		}
		
		
	}
	
	public void get(Long id) {
		
		bankAccountDao.getById(id);
		
	}
	
	
	

}
