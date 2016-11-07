package com.vamendrik.training.banking.datamodel;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Transaction extends AbstractModel {
	
	private Timestamp dateCompletion;
	private boolean status;
	private double sum;
	private Long creditCardId;
	private Long fromTo;
	
	public Timestamp getDateCompletion() {
		return dateCompletion;
	}
	
	public void setDateCompletion(Timestamp date) {
		this.dateCompletion = date;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public double getSum() {
		return sum;
	}
	
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public Long getCreditCardId() {
		return creditCardId;
	}
	
	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}

	public Long getFromTo() {
		return fromTo;
	}

	public void setFromTo(Long fromTo) {
		this.fromTo = fromTo;
	}

}
