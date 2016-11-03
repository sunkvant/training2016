package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.ClientDao;
import com.vamendrik.training.banking.daodb.AutorizationDao;
import com.vamendrik.training.banking.datamodel.Client;
import com.vamendrik.training.banking.datamodel.Autorization;

@Service
public class UserServiceImpl {
	
	@Inject
	private ClientDao clientDao;
	
	@Inject
	private AutorizationDao autorizationDao;
	
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
	
	public List<Client> getAllClients() {
		
		
		return clientDao.getAll();
		
		
	}
	
	
	public List<Autorization> getAllClientsAutorization() {
		
		
		return autorizationDao.getAll();
		
		
	}
	
	
	public void add(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId,Long bankAccountId,String login,Long roleId) {
		
		Client client=new Client();
		Autorization autorization=new Autorization();
		
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setMiddleName(middleName);
		client.setNumberOfPassport(numberOfPassport);
		client.setDateBorn(dateBorn);
		client.setCityId(cityId);
		
		autorization.setLogin(login);
		autorization.setPassword(generatePassword());
		autorization.setRoleId(roleId);
		
		clientDao.insert(client);
		autorizationDao.insert(autorization);
		
		
	}
	

}
