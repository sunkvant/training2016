package com.vamendrik.training.banking.datamodel;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public class BankAccount extends AbstractModel {
	
	@Field(name="number_account")
	private Long numberAccount;
	
	@Field(name="sum")
	private Double sum;
	
	@Field(name="status")
	private Boolean status;
	
	@Field(name="user_id")
	private Long userId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BankAccount [NumberAccount=" + numberAccount + ", Id=" + getId() + ", UserId=" + userId;
	}

}
