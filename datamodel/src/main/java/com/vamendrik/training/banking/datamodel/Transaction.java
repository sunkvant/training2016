package com.vamendrik.training.banking.datamodel;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public class Transaction extends AbstractModel {
	
	@Field(name="date_completion")
	private Timestamp dateCompletion;
	
	@Field(name="status")
	private Boolean status;
	
	@Field(name="sum")
	private Double sum;
	
	@Field(name="credit_card_id")
	private Long creditCardId;
	
	@Field(name="from_to")
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
