package com.vamendrik.training.banking.datamodel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public class User extends AbstractModel {

	@Field(name="first_name")
	private String firstName;
	
	@Field(name="last_name")
	private String lastName;
	
	@Field(name="middle_name")
	private String middleName;
	
	@Field(name="number_of_passport")
	private String numberOfPassport;
	
	@Field(name="login")
	private String login;
	
	@Field(name="password")
	private String password;
	
	@Field(name="date_born")
	private Date dateBorn;
	
	@Field(name="city_id")
	private Long cityId;
	
	private List<Role> roles;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [Username=" + login + ", FirstName=" + firstName + ", LastName=" + lastName + ", MiddleName="
				+ middleName + ", Id=" + getId() + "]";
	}

}
