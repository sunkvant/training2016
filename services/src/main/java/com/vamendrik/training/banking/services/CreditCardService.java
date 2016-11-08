package com.vamendrik.training.banking.services;

import com.vamendrik.training.banking.datamodel.CreditCard;

public interface CreditCardService extends AbstractService<CreditCard,Long> {
	
	public void add(Long bankAccountId);

}
