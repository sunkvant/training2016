package com.vamendrik.training.banking.datamodel;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public class City extends AbstractModel {
	
	@Field(name="city_name")
	private String cityName;
	
	@Field(name="country_id")
	private Long coutryId;
	
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Long getCountryId() {
		return coutryId;
	}
	
	public void setCoutryId(Long coutryId) {
		this.coutryId = coutryId;
	}
	
	

}
