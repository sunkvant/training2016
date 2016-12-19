package com.vamendrik.training.banking.datamodel;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public class Country extends AbstractModel {
	
	@Field(name="country_name")
	private String countryName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	

}
