package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Role;

public interface RoleService extends AbstractService<Role,Long> {

	public Long add(String roleName);

	
}
