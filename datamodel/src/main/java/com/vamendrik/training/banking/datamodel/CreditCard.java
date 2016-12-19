package com.vamendrik.training.banking.datamodel;

import java.util.Date;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public class CreditCard extends AbstractModel {
	
	@Field(name="number_card")
	private Long numberCard;
	
	@Field(name="validity")
	private Date validity;
	
	@Field(name="status")
	private Boolean status;
	
	@Field(name="bank_account_id")
	private Long bankAccountId;
	
	public Long getNumberCard() {
		return numberCard;
	}
	public void setNumberCard(Long numberCard) {
		this.numberCard = numberCard;
	}
	
	public Date getValidity() {
		return validity;
	}
	
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Long getBankAccountId() {
		return bankAccountId;
	}
	
	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

}
