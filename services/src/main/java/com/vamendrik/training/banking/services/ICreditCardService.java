package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.CreditCard;

public interface ICreditCardService extends IAbstractService<CreditCard,Long> {
	
	public List<CreditCard> getAllByBankAccountId(Long bankAccountId);
	public List<CreditCard> getAllByUserId(Long userId);
	public CreditCard getByNumberCard(Long numberCard);
	public CreditCard getLast();
	public void createCreditCard(Long bankAccountId);

}
