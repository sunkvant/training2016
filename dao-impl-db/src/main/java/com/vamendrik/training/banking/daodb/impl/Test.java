package com.vamendrik.training.banking.daodb.impl;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.vamendrik.training.banking.datamodel.Client;


public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ctx= new FileSystemXmlApplicationContext("src/main/resources/context.xml");
		
		ClientDaoImpl bn = (ClientDaoImpl) ctx.getBean("clientDaoImpl");
		
		List<Client> list=bn.getAll();
		
		for(int i=0; i<list.size(); i++) 
			System.out.println(list.get(i).getFirstName()+" "+list.get(i).getLastName()+" "+list.get(i).getMiddleName()+" "+list.get(i).getNumberOfPassport()+" "+list.get(i).getCityId());

		Client client=bn.getById(2l);
		System.out.println(client.getFirstName()+" "+client.getLastName()+" "+client.getMiddleName()+" "+client.getNumberOfPassport()+" "+client.getDateBorn().toString()+" "+client.getCityId());
		
		//client.setFirstName("Степан");
		
		//bn.update(client);
		
}
	
}
