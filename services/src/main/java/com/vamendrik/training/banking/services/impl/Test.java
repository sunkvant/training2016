package com.vamendrik.training.banking.services.impl;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Test {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx= new FileSystemXmlApplicationContext("src/main/resources/context.xml");
		for(int i=0; i<ctx.getBeanDefinitionCount(); i++) 
			System.out.println(ctx.getBeanDefinitionNames()[i]);
		UserServiceImpl imp=(UserServiceImpl) ctx.getBean("userServiceImpl");
		imp.add("Павел", "Бугуртов", "Рудольфович", "KH45643", new Date(), 2l, "Petuh", "RTyu6734", 1l);
		
		
	}
	
	
	
}
