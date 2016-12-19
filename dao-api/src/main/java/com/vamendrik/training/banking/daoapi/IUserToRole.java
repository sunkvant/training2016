package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Role;

public interface IUserToRole<T> {
	
	public void setRolesUser(T userId,List<Role> roles);
	
	public void deleteRolesUser(Long userId);
}
