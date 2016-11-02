package com.vamendrik.training.banking.datamodel;

import java.util.Date;

public class Client extends AbstractModel {
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String numberOfPassport;
	private Date dateBorn;
	private Long cityId;
	private Long bankAccountId;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getNumberOfPassport() {
		return numberOfPassport;
	}
	
	public void setNumberOfPassport(String numberOfPassport) {
		this.numberOfPassport = numberOfPassport;
	}
	
	public Date getDateBorn() {
		return dateBorn;
	}
	
	public void setDateBorn(Date dateBorn) {
		this.dateBorn = dateBorn;
	}
	
	public Long getCityId() {
		return cityId;
	}
	
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}
	
	

}
