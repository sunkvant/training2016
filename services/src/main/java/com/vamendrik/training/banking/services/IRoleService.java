package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Role;

public interface IRoleService extends IAbstractService<Role,Long> {

	public Role getByRoleType(String roleType);
	public List<Role> getAllByUserId(Long userId);
	public Long add(String roleName);

	
}
