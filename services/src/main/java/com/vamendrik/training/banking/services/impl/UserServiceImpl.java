package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamendrik.training.banking.daoapi.IUserDao;
import com.vamendrik.training.banking.datamodel.User;
import com.vamendrik.training.banking.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private IUserDao clientDao;
	
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
	public List<User> getAllClients() {
		
		
		return clientDao.getAll();
		
		
	}
	
	@Override
	public void delete(User client) {
		
		autorizationDao.delete(autorizationDao.getById(client.getId()));
		clientDao.delete(client);
		
		
		
	}
	
	@Override
	public List<Autorization> getAllClientsAutorization() {
		
		
		return autorizationDao.getAll();
		
		
	}
	
	@Override
	public User getClient(Long id) {
		
		
		return clientDao.getById(id);
		
	}
	
	@Override
	public Autorization getAutorization(Long id) {
		
		
		return autorizationDao.getById(id);
		
	}
	
	@Override
	@Transactional
	public Long add(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId,String login,Long roleId) {
		
		User client=new User();
		Autorization autorization=new Autorization();
		
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setMiddleName(middleName);
		client.setNumberOfPassport(numberOfPassport);
		client.setDateBorn(dateBorn);
		client.setCityId(cityId);
		
		Long key=clientDao.insert(client);
		
		autorization.setId(key);
		autorization.setLogin(login);
		autorization.setPassword(generatePassword());
		autorization.setRoleId(roleId);
		
		
		autorizationDao.insert(autorization);
		
		return key;
		
		
	}
	

}
