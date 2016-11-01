package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.AbstractDao;
import com.vamendrik.training.banking.daodb.ClientDao;
import com.vamendrik.training.banking.daodb.UserDao;
import com.vamendrik.training.banking.daodb.impl.ClientDaoImpl;
import com.vamendrik.training.banking.daodb.impl.UserDaoImpl;
import com.vamendrik.training.banking.datamodel.Client;
import com.vamendrik.training.banking.datamodel.User;

@Service
public class UserServiceImpl {
	
	@Inject
	private ClientDao clientDao;
	
	@Inject
	private UserDao userDao;
	
	private void addClient(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId) {
		
		Client client=new Client();
		
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setMiddleName(middleName);
		client.setNumberOfPassport(numberOfPassport);
		client.setDateBorn(dateBorn);
		client.setCityId(cityId);
		
		clientDao.insert(client);
		
		
		
	}
	
	private void addUser(String login,String password,Long roleId) {
		
		User user=new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setRoleId(roleId);
		
		userDao.insert(user);
		
		
	}
	
	public void add(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId,String login,String password,Long roleId) {
		
		//addClient(firstName,lastName,middleName,numberOfPassport,dateBorn,cityId);
		addUser(login,password,roleId);
		
		
	}
	

}
