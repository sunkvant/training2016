package com.vamendrik.training.banking.services.impl;

import java.util.Date;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vamendrik.training.banking.daodb.ClientDao;
import com.vamendrik.training.banking.datamodel.Autorization;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Client;


public class Test {
	

	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		ApplicationContext ctx= new FileSystemXmlApplicationContext("src/main/resources/context.xml");
		for(int i=0; i<ctx.getBeanDefinitionCount(); i++) 
			System.out.println(ctx.getBeanDefinitionNames()[i]);
//		CountryServiceImpl imp=(CountryServiceImpl) ctx.getBean("countryServiceImpl");
//		
//		imp.add("Россия");
//		imp.add("Беларусь");
//		imp.add("США");
//		
//		CityServiceImpl imp2=(CityServiceImpl) ctx.getBean("cityServiceImpl");
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
//	
//		RoleServiceImpl imp3=(RoleServiceImpl) ctx.getBean("roleServiceImpl");
//		
//		imp3.add("Администратор");
//		imp3.add("Пользователь");
//		imp3.add("Клиент");
		
		
	
		
	
		
		UserServiceImpl imp4=(UserServiceImpl) ctx.getBean("userServiceImpl");
		
		Calendar calendar=Calendar.getInstance();
		
		calendar.add(Calendar.YEAR, 3);
		
		Date date=new Date(calendar.getTimeInMillis());
		
		
		
		System.out.println(date.getHours());
//	
//		imp4.add("Антон", "Бандура", "Александрович", "KH45563", new Date(), 5l, "Anton", 2l);
//		imp4.add("Павел", "Бутрудинов", "Михайлович", "KH90363", new Date(), 3l, "pasha", 1l);
		

		

		 
		BankAccountServiceImpl imp5=(BankAccountServiceImpl) ctx.getBean("bankAccountImpl");
		
//		imp5.add(0, true, imp4.getAllClients().get(0).getId());
//		imp5.add(0, true, imp4.getAllClients().get(1).getId());
		
		CreditCardServiceImpl imp6=(CreditCardServiceImpl) ctx.getBean("creditCardService");
		
//		imp6.add(imp5.getAll().get(0).getId());
//		imp6.add(imp5.getAll().get(0).getId());
//		imp6.add(imp5.getAll().get(1).getId());
		
		TransactionServiceImpl imp7=(TransactionServiceImpl) ctx.getBean("transactionServiceImpl");
		
		imp7.TransferToBankAccount(imp6.getAll().get(0), imp6.getAll().get(1), 546.7);
		
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
