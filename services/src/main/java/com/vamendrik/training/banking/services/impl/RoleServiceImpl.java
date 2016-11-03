package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.RoleDao;
import com.vamendrik.training.banking.datamodel.Role;

@Service
public class RoleServiceImpl {

	@Inject
	RoleDao roleDao;
	
	public List<Role> getAll() {
		
		
		return roleDao.getAll();
		
	}
	
	public void add(String roleName) {
		
		Role role=new Role();
		
		role.setRoleName(roleName);
		
		roleDao.insert(role);
	}
	
	public void delete(Role role) {
		
		roleDao.delete(role);
		
	}
	
	public void save(Role role) {
		
		
		if (roleDao.getById(role.getId())==null) {
			
			roleDao.insert(role);
			
		} else {
			
			roleDao.update(role);
			
		}
		
		
	}
	
	
	public Role get(Long id) {
		
		
		return roleDao.getById(id);
		
		
	}
	

}

