package com.vamendrik.training.banking.daodb.impl.abstractions;

import java.util.List;
import java.util.Map;

public interface IAnnotationAnalizator {

	Class initialize(Class object) throws ClassNotFoundException;

	List<String> getFields();
	
	Map<String, String> getMapFields();

	String getTableName();

}