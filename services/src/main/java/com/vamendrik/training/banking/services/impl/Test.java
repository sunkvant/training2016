package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vamendrik.training.banking.datamodel.Autorization;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Client;


public class Test {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx= new FileSystemXmlApplicationContext("src/main/resources/context.xml");
		for(int i=0; i<ctx.getBeanDefinitionCount(); i++) 
			System.out.println(ctx.getBeanDefinitionNames()[i]);
		CountryServiceImpl imp=(CountryServiceImpl) ctx.getBean("countryServiceImpl");
		
//		imp.add("Россия");
//		imp.add("Беларусь");
//		imp.add("США");
		
		CityServiceImpl imp2=(CityServiceImpl) ctx.getBean("cityServiceImpl");
//		imp2.add("Москва", 1l);
//		imp2.add("Смоленск", 1l);
//		imp2.add("Санкт-Петербург", 1l);
//		
//		imp2.add("Минск", 2l);
//		imp2.add("Гродно", 2l);
//		imp2.add("Гомель", 2l);
//		
//		imp2.add("Вашингтон", 3l);
//		imp2.add("Нью-Йорк", 3l);
//		imp2.add("Сан-Франциско", 3l);
		
//		City city=imp2.get(4l);
//		city.setCityName("Минск");
//		
//		imp2.save(city);
//		
//		city=imp2.get(5l);
//		city.setCityName("Гродно");
//		
//		imp2.save(city);
//		
//		city=imp2.get(6l);
//		city.setCityName("Гомель");
//		
//		imp2.save(city);
		
		RoleServiceImpl imp3=(RoleServiceImpl) ctx.getBean("roleServiceImpl");
		
		imp3.add("Администратор");
		imp3.add("Пользователь");
		imp3.add("Клиент");
		
	
//		//imp.addUser("Антон", "Бандура", "Александрович", "KH45563", new Date(), 5l, "Anton", 2l);
//		List<Autorization> inf=imp.getAllClientsAutorization();
//		
//		List<Client> inf2=imp.getAllClients();
//		
//		for(int i=0; i<inf.size(); i++) {
//			
//			System.out.println(String.format("%s %s %s %s %s %s %s", inf2.get(i).getFirstName(),inf2.get(i).getLastName(),inf2.get(i).getMiddleName(),
//					inf2.get(i).getNumberOfPassport(),inf2.get(i).getDateBorn(),inf.get(i).getLogin(),inf.get(i).getPassword()));
//			
//		}
		
		
		
		
	}
	
	
	
}
