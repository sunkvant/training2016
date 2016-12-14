package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daoapi.IRoleDao;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Inject
	IRoleDao roleDao;
	
	@Override
	public List<Role> getAll() {

		return roleDao.getAll();

	}

	@Override
	public Long add(String roleName) {

		Role role = new Role();

		role.setRoleName(roleName);

		return roleDao.insert(role);
	}

	@Override
	public void delete(Role role) {

		roleDao.delete(role);

	}

	@Override
	public void save(Role role) {

		if (roleDao.getById(role.getId()) == null) {

			roleDao.insert(role);

		} else {

			roleDao.update(role);

		}

	}

	@Override
	public Role get(Long id) {

		return roleDao.getById(id);

	}

	@Override
	public Role getByRoleType(String roleType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
