package com.vamendrik.training.banking.datamodel;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public abstract class AbstractModel {
	
	@Field(name="id")
	private Long id;
	
	@Field(name="is_delete")
	private Boolean delete; 

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	

}
