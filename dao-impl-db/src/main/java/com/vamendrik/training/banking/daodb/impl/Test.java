package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vamendrik.training.banking.daoapi.ICityDao;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.datamodel.User;


public class Test {

	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {

		ApplicationContext ctx=new ClassPathXmlApplicationContext("root-context.xml");
		
		
		TestAnnotation bn = (TestAnnotation) ctx.getBean("testAnnotation");

		
		
//		City city=new City();
//		
//		city.setCityName("Гомель");
//		city.setCoutryId(1l);
//		city.setDelete(false);
//		
//		
//		System.out.println("Iserted with key="+bn.insert(city));

		List<City> list=bn.rt();
		for(int i=0; i<list.size(); i++) {
			
			System.out.println(list.get(i).getId());
			System.out.println(list.get(i).getCityName());
			System.out.println(list.get(i).getCountryId());
			System.out.println(list.get(i).getDelete());
		}
		
//		list.get(0).setCityName("Витебск");
//		
//		bn.update(list.get(0));
//		
//		list=bn.getAll();
//		
//		for(int i=0; i<list.size(); i++) {
//			
//			System.out.println(list.get(i).getId());
//			System.out.println(list.get(i).getCityName());
//			System.out.println(list.get(i).getCountryId());
//			System.out.println(list.get(i).isDelete());
//		}
		
		
}
	
}
