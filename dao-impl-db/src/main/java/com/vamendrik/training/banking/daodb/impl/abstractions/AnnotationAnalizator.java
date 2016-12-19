package com.vamendrik.training.banking.daodb.impl.abstractions;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vamendrik.training.banking.datamodel.annotations.Field;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Component
public class AnnotationAnalizator implements IAnnotationAnalizator {

	private List<String> fields;
	private Map<String, String> mapFields;
	
	private String tableName;

	/* (non-Javadoc)
	 * @see com.vamendrik.training.banking.daodb.impl.IAnnotation#initialize(java.lang.Class)
	 */
	@Override
	public Class initialize(Class object) throws ClassNotFoundException {

		if (!object.isAnnotationPresent(Entity.class))
			return object; 

		Annotation annotation = object.getAnnotation(Entity.class);
		Entity refactor = (Entity) annotation;
		object = Class.forName("com.vamendrik.training.banking.datamodel." + refactor.object());
		this.tableName=refactor.table();

		this.fields=new ArrayList<String>();
		this.mapFields=new HashMap<String,String>();
		setFieldsAndMapFields(object.getSuperclass());
		setFieldsAndMapFields(object);

		return object; 

	}
	
	private void setFieldsAndMapFields(Class object) {
		

		for (int i = 0; i < object.getDeclaredFields().length; i++) {

			if (object.getDeclaredFields()[i].isAnnotationPresent(Field.class)) {

				Annotation annotation = object.getDeclaredFields()[i].getAnnotation(Field.class);
				Field field = (Field) annotation;
				this.fields.add(object.getDeclaredFields()[i].getName());
				this.mapFields.put(object.getDeclaredFields()[i].getName(), field.name());
			}

		}
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.vamendrik.training.banking.daodb.impl.IAnnotation#getFields()
	 */
	@Override
	public List<String> getFields() {
		
		return fields;
		
	}
	
	/* (non-Javadoc)
	 * @see com.vamendrik.training.banking.daodb.impl.IAnnotation#getMapFields()
	 */
	@Override
	public Map<String, String> getMapFields() {
		
		return mapFields;
		
	}
	
	/* (non-Javadoc)
	 * @see com.vamendrik.training.banking.daodb.impl.IAnnotation#getTableName()
	 */
	@Override
	public String getTableName() {
		
		return tableName;
		
	}
	
	
}
