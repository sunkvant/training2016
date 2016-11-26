package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.datamodel.User;


public class Test {
	

	public static void main(String[] args) {
		
		ApplicationContext ctx= new FileSystemXmlApplicationContext("src/main/resources/context.xml");
		
		
		UserDaoImpl bn = (UserDaoImpl) ctx.getBean("userDaoImpl");
		CountryDaoImpl cn = (CountryDaoImpl) ctx.getBean("countryDaoImpl");
		
		for(int i=0; i<ctx.getBeanDefinitionCount(); i++) 
			System.out.println(ctx.getBeanDefinitionNames()[i]);
		
		Country country=cn.getByCountryName("США");
		List<User> list=bn.getAllByCountryId(country.getId()); 
		
		 
		
		for(int i=0; i<list.size(); i++) 
			System.out.println(list.get(i).getFirstName()+" "+list.get(i).getLastName()+" "+list.get(i).getMiddleName()+" "+list.get(i).getNumberOfPassport()+" "+list.get(i).getCityId());

		//User client=bn.getById(2l);
		//System.out.println(client.getFirstName()+" "+client.getLastName()+" "+client.getMiddleName()+" "+client.getNumberOfPassport()+" "+client.getDateBorn().toString()+" "+client.getCityId());
		
		//client.setFirstName("Степан");
		
		//bn.update(client);
		
		
		
}
	
}
