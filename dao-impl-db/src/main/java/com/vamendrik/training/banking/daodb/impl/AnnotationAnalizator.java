package com.vamendrik.training.banking.daodb.impl;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vamendrik.training.banking.datamodel.Field;
import com.vamendrik.training.banking.datamodel.Refactor;

public class AnnotationAnalizator {

	private static List<String> fields=new ArrayList<String>();
	private static Map<String, String> mapFields=new HashMap<String,String>();
	
	private static List<String> fieldsSuperClass=new ArrayList<String>();
	private static Map<String, String> mapFieldsSuperClass=new HashMap<String,String>();
	
	private static String tableName;

	public static Class initialize(Class object) throws ClassNotFoundException {

		if (!object.isAnnotationPresent(Refactor.class))
			return object; 

		Annotation annotation = object.getAnnotation(Refactor.class);
		Refactor refactor = (Refactor) annotation;
		System.out.println(refactor.object());
		object = Class.forName("com.vamendrik.training.banking.datamodel." + refactor.object());
		tableName=refactor.table();
		System.out.println(object.getDeclaredFields().length);
		for (int i = 0; i < object.getDeclaredFields().length; i++) {

			if (object.getDeclaredFields()[i].isAnnotationPresent(Field.class)) {

				annotation = object.getDeclaredFields()[i].getAnnotation(Field.class);
				Field field = (Field) annotation;
				fields.add(object.getDeclaredFields()[i].getName());
				mapFields.put(object.getDeclaredFields()[i].getName(), field.name());
			}

		}
		
		for (int i = 0; i < object.getSuperclass().getDeclaredFields().length; i++) {

			if (object.getSuperclass().getDeclaredFields()[i].isAnnotationPresent(Field.class)) {

				annotation = object.getSuperclass().getDeclaredFields()[i].getAnnotation(Field.class);
				Field field = (Field) annotation;
				fieldsSuperClass.add(object.getSuperclass().getDeclaredFields()[i].getName());
				mapFieldsSuperClass.put(object.getSuperclass().getDeclaredFields()[i].getName(), field.name());
			}

		}
		return object; 

	}
	
	public static List<String> getFields() {
		
		return fields;
		
	}
	
	public static Map<String, String> getMapFields() {
		
		return mapFields;
		
	}
	
	public static List<String> getFieldsSuperClass() {
		
		return fieldsSuperClass;
		
	}
	
	public static Map<String, String> getMapFieldsSuperClass() {
		
		return mapFieldsSuperClass;
		
	}
	
	public static String getTableName() {
		
		return tableName;
		
	}
	
	
}
