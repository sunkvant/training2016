package com.vamendrik.training.banking.datamodel;

public abstract class AbstractModel {
	
	@Field(name="id")
	private Long id;
	
	@Field(name="is_delete")
	private boolean isDelete; 

	public Long getId() {
		return id;
	}
	
	@Setter(field="id")
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDelete() {
		return isDelete;
	}

	@Setter(field="is_delete")
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	

}
