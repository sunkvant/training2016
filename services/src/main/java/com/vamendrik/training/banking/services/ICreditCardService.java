package com.vamendrik.training.banking.services;

import com.vamendrik.training.banking.datamodel.CreditCard;

public interface ICreditCardService extends IAbstractService<CreditCard,Long> {
	
	public void add(Long bankAccountId);

}
