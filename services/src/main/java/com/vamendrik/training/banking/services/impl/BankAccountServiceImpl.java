package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.BankAccountDao;
import com.vamendrik.training.banking.datamodel.BankAccount;

@Service
public class BankAccountServiceImpl {
	
	@Inject
	BankAccountDao bankAccountDao;
	
	
	private Long generateNumberAccount() {
		
		
		if (bankAccountDao.getLast()==null) {
			
			return 368591075l;
		
		} else {
			
			return bankAccountDao.getLast().getNumberAccount()+new Date().getSeconds()+1;
			
		}
		
		
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
		
		bankAccountDao.insert(bankAccount);
		
		
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
	
	public BankAccount get(Long id) {
		
		return bankAccountDao.getById(id);
		
	}
	
	
	

}
