package com.vamendrik.training.banking.daoapi;

import com.vamendrik.training.banking.datamodel.Role;

public interface IRoleDao extends IAbstractDao<Role,Long> {
	
	public Role getByRoleType(String roleType);

}
