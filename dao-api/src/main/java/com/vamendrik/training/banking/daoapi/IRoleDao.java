package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Role;

public interface IRoleDao extends IAbstractDao<Role,Long> {
	
	public Role getByRoleType(String roleType);
	public List<Role> getAllByUserId(Long userId);

}
