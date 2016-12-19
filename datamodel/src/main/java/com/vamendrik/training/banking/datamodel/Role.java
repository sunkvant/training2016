package com.vamendrik.training.banking.datamodel;

import com.vamendrik.training.banking.datamodel.annotations.Field;

public class Role extends AbstractModel {
	
	@Field(name="role_type")
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
