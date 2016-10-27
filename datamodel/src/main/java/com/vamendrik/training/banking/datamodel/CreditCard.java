package com.vamendrik.training.banking.datamodel;

import java.sql.Date;

public class CreditCard extends AbstractModel {
	
	private String numberCard;
	private Date validity;
	private boolean status;
	private Long bankAccountId;
	private Long clientId;
	public String getNumberCard() {
		return numberCard;
	}
	public void setNumberCard(String numberCard) {
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
	
	public Long getClientId() {
		return clientId;
	}
	
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
