package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.datamodel.User;


public class Test {
	

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("root-context.xml");
		
		
		TestAnnotation bn = (TestAnnotation) ctx.getBean("testAnnotation");
		List<City> list=bn.getAll();
		for(int i=0; i<list.size(); i++) {
			
			System.out.println(list.get(i).getCityName());
			
		}

		
		
		
}
	
}
