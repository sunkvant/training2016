package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vamendrik.training.banking.datamodel.Autorization;
import com.vamendrik.training.banking.datamodel.Client;


public class Test {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx= new FileSystemXmlApplicationContext("src/main/resources/context.xml");
		for(int i=0; i<ctx.getBeanDefinitionCount(); i++) 
			System.out.println(ctx.getBeanDefinitionNames()[i]);
		UserServiceImpl imp=(UserServiceImpl) ctx.getBean("userServiceImpl");
		//imp.addUser("Антон", "Бандура", "Александрович", "KH45563", new Date(), 5l, "Anton", 2l);
		List<Autorization> inf=imp.getAllClientsAutorizationInfo();
		
		List<Client> inf2=imp.getAllClients();
		
		for(int i=0; i<inf.size(); i++) {
			
			System.out.println(String.format("%s %s %s %s %s %s %s", inf2.get(i).getFirstName(),inf2.get(i).getLastName(),inf2.get(i).getMiddleName(),
					inf2.get(i).getNumberOfPassport(),inf2.get(i).getDateBorn(),inf.get(i).getLogin(),inf.get(i).getPassword()));
			
		}
		
		
	}
	
	
	
}
