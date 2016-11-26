package com.vamendrik.training.banking.datamodel;

public abstract class AbstractModel {
	
	private Long id;
	private boolean isDelete; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	

}
