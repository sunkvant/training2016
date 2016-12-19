package com.vamendrik.training.banking.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daoapi.ICreditCardDao;
import com.vamendrik.training.banking.datamodel.CreditCard;
import com.vamendrik.training.banking.services.ICreditCardService;

@Service
public class CreditCardServiceImpl implements ICreditCardService {
	
	@Inject
	private ICreditCardDao creditCardDao;
	
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
	
	public void createCreditCard(Long bankAccountId) {
		
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
	
	public CreditCard get(Long id) {
		
		return creditCardDao.getById(id);
		
	}

	@Override
	public List<CreditCard> getAllByNumberBankAccount(Long numberAccount) {

		return creditCardDao.getAllByNumberBankAccount(numberAccount);
	}

	@Override
	public List<CreditCard> getAllByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditCard getByNumberCard(Long numberCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditCard getLast() {
		// TODO Auto-generated method stub
		return null;
	}


}
