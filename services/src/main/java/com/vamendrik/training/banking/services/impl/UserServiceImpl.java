package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamendrik.training.banking.daoapi.IRoleDao;
import com.vamendrik.training.banking.daoapi.IUserDao;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.User;
import com.vamendrik.training.banking.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Inject
	private IUserDao userDao;
	
	@Inject
	private IRoleDao roleDao;
	
	private String generatePassword() {
		
		String s="abcdefghijklmnopqrstuvwxyz0123456789";
		
		StringBuilder stringBuilder=new StringBuilder();
		
		Random rnd=new Random();
		
		for(int i=0; i<10; i++) {
			
			char c=s.charAt(rnd.nextInt(s.length()-1));
			if (rnd.nextInt(10)>5) 
				c=Character.toUpperCase(c);
			stringBuilder.append(c);
		
		}
		
		return stringBuilder.toString();
		
	}
	
	@Override
	public List<User> getAll() {
		
		
		List<User> users = userDao.getAll();
		
		
		
		for(int i=0; i<users.size(); i++) {
			
			List<Role> roles=roleDao.getAllByUserId(users.get(i).getId());
			users.get(i).setRoles(roles);

			
		}
		
		return users;
		
		
	}
	
	@Override
	public void delete(User client) {
		
		userDao.delete(client);
		
		
		
	}
	
	
	@Override
	public User get(Long id) {
		
		User user = userDao.getById(id);
		
		user.setRoles(roleDao.getAllByUserId(user.getId()));
		
		return user;
		
	}
	
	@Override
	@Transactional
	public Long createUser(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId,String login,String password,List<Role> roles) {
		
		User user=new User();
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMiddleName(middleName);
		user.setNumberOfPassport(numberOfPassport);
		user.setDateBorn(dateBorn);
		user.setCityId(cityId);
		user.setLogin(login);
		user.setPassword(password);
		user.setRoles(roles);
		
		Long key=userDao.insert(user);
		
		
		
		
		return key;
		
		
	}


	@Override
	public void save(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAllByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllByCityId(Long cityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllByCountryId(Long countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByNumberPassport(String numberOfPassport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByLogin(String login) {
		return userDao.getByLogin(login);
	}
	

}
