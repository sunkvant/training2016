package com.vamendrik.training.banking.datamodel;

public class BankAccount extends AbstractModel {
	
	private String numberAccount;
	private double sum;
	private boolean status;
	
	public String getNumberAccount() {
		return numberAccount;
	}
	
	public void setNumberAccount(String numberAccount) {
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

}
