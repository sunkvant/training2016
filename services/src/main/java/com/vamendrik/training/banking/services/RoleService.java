package com.vamendrik.training.banking.services;

import java.util.List;

import com.vamendrik.training.banking.datamodel.Role;

public interface RoleService {

	public List<Role> getAll();
	public Long add(String roleName);
	public void delete(Role role);
	public void save(Role role);
	public Role get(Long id);
	
}
