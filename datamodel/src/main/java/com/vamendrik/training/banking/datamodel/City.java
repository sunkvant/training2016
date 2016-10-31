package com.vamendrik.training.banking.datamodel;

public class City extends AbstractModel {
	
	private String cityName;
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
