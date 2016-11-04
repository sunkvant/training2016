package com.vamendrik.training.banking.datamodel;

public class BankAccount extends AbstractModel {
	
	private Long numberAccount;
	private double sum;
	private boolean status;
	private Long clientId;
	
	public Long getNumberAccount() {
		return numberAccount;
	}
	
	public void setNumberAccount(Long numberAccount) {
		this.numberAccount = numberAccount;
	}
	
	public double getSum() {
		return sum;
	}
	
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
