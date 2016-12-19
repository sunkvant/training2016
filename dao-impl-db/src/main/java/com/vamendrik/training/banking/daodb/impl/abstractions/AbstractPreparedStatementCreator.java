package com.vamendrik.training.banking.daodb.impl.abstractions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class AbstractPreparedStatementCreator<E> implements PreparedStatementCreator {

	
	private String insertSQL;
	private E instance;
	private Class object;
	private IAnnotationAnalizator annotationAnalizator;
	private int preparedStatementCounter;
	private boolean keyHolder;
	
	private List<String> fields;
	private Map<String, String> mapFields;
	
	public AbstractPreparedStatementCreator(String SQL,Class object,E instance,IAnnotationAnalizator annotationAnalizator,boolean keyHolder) {
		
		
		this.insertSQL=SQL;
		this.instance=instance;
		this.object=object;
		this.annotationAnalizator=annotationAnalizator;
		this.keyHolder=keyHolder;
		
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement ps;
		if(keyHolder) {
			ps=con.prepareStatement(insertSQL, new String[] { "id" });
		
		} else ps=con.prepareStatement(insertSQL);
		
		this.fields=annotationAnalizator.getFields();
		this.mapFields = annotationAnalizator.getMapFields();
		this.mapFields.remove("id");
		this.fields.remove("id");
		

		
		this.preparedStatementCounter=1;
		
		try {
			getFieldInstance(object.getSuperclass(), ps);
			getFieldInstance(object, ps);
			
			if (!keyHolder) {
				
				java.lang.reflect.Field field = object.getSuperclass().getDeclaredField("id");

				field.setAccessible(true);
				ps.setObject(this.preparedStatementCounter, field.get(this.instance));
				

			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return ps;
	}
	
	
	private void getFieldInstance(Class object,PreparedStatement ps) throws IllegalArgumentException, IllegalAccessException, SQLException {
		
		for (int i = 0; i < object.getDeclaredFields().length; i++) {
			
			if (this.fields.contains(object.getDeclaredFields()[i].getName())) {
				
				java.lang.reflect.Field field = object.getDeclaredFields()[i];

				field.setAccessible(true);
				ps.setObject(this.preparedStatementCounter, field.get(this.instance));
				
				this.preparedStatementCounter++;
				
			}

		}
		
	}

}
