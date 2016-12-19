package com.vamendrik.training.banking.daodb.impl.abstractions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AbstractMapper<E> implements RowMapper<E> {

	private IAnnotationAnalizator annotationAnalizator;

	private Class object;
	private E instance;
	
	private List<String> fields;
	private Map<String, String> mapFields;

	public AbstractMapper(Class object, IAnnotationAnalizator annotationAnalizator) {

		this.object = object;
		this.annotationAnalizator = annotationAnalizator;
	}

	@Override
	public E mapRow(ResultSet rs, int rowNum) throws SQLException {
		

		try {
			this.fields=annotationAnalizator.getFields();
			this.mapFields = annotationAnalizator.getMapFields();
			this.instance = (E) object.newInstance();
			
			setFieldInstance(this.object.getSuperclass(),rs);
			setFieldInstance(this.object,rs);


			return (E) this.instance;

		} catch (SecurityException | IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {

			e.printStackTrace();
		}
		return null;
	}

	private void setFieldInstance(Class object,ResultSet rs) throws IllegalArgumentException, IllegalAccessException, SQLException {
		
		for (int i = 0; i < object.getDeclaredFields().length; i++) {
			
			if (this.fields.contains(object.getDeclaredFields()[i].getName())) {
				
				java.lang.reflect.Field field = object.getDeclaredFields()[i];

				field.setAccessible(true);
				field.set(this.instance, rs.getObject(this.mapFields.get(object.getDeclaredFields()[i].getName())));
				
			}

		}
		
	}

}
