package com.vamendrik.training.banking.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.CreditCardDao;
import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.CreditCard;

@Service
public class CreditCardService {
	
	@Inject
	private CreditCardDao creditCardDao;
	
	private Long generateNumberCard() {
		
		if (creditCardDao.getLast()==null) {
		
			return 1573066859107567l;
		
		} else {
			
			return creditCardDao.getLast().getNumberCard()+new Date().getSeconds()+1;
			
		}
		
	}
	
	public List<CreditCard> getAll() {
		
		
		return creditCardDao.getAll();
		
	}
	
	public void add(Long bankAccountId) {
		
		Calendar calendar=Calendar.getInstance();
		
		calendar.add(Calendar.YEAR, 3);
		
		CreditCard creditCard=new CreditCard();
		
		creditCard.setNumberCard(generateNumberCard());
		creditCard.setValidity(new Date(calendar.getTimeInMillis()));
		creditCard.setBankAccountId(bankAccountId);
		creditCard.setStatus(true);
		
		creditCardDao.insert(creditCard);
		
	}
	
	public void delete(CreditCard creditCard) {
		
		
		creditCardDao.delete(creditCard);
		
	}
	
	
	public void save(CreditCard creditCard) {
		
		if (creditCardDao.getById(creditCard.getId())==null) {
			
			creditCardDao.insert(creditCard);
			
			
		} else {
			
			
			creditCardDao.update(creditCard);
			
		}
		
		
	}
	
	public void get(Long id) {
		
		creditCardDao.getById(id);
		
	}

}
