package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.CreditCard;

public interface ICreditCardDao extends IAbstractDao<CreditCard,Long> {
	
	public List<CreditCard> getAllByBankAccountId(Long bankAccountId);
	public List<CreditCard> getAllByUserId(Long userId);
	public CreditCard getByNumberCard(Long numberCard);
	public CreditCard getLast();
	
}
