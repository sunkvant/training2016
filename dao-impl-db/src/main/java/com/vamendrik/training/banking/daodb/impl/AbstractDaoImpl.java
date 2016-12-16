package com.vamendrik.training.banking.daodb.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.daoapi.IAbstractDao;
import com.vamendrik.training.banking.daodb.mapper.CityMapper;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Field;
import com.vamendrik.training.banking.datamodel.Refactor;
import com.vamendrik.training.banking.datamodel.Setter;

public abstract class AbstractDaoImpl<E, T> implements IAbstractDao<E, T> {

	@Inject
	private JdbcTemplate jdbcTemplate;
	private Class object;
	private E obj12;

	protected Class getCurrentClass() {

		return this.getClass();

	}

	@Override
	public List<E> getAll() throws ClassNotFoundException, NoSuchFieldException, SecurityException,
			InstantiationException, IllegalAccessException {

		final Class obj = getCurrentClass();

		if (obj.isAnnotationPresent(Refactor.class)) {

			Annotation annotation = obj.getAnnotation(Refactor.class);

			Refactor refactor = (Refactor) annotation;

			System.out.println(refactor.object());

			object =  Class.forName("com.vamendrik.training.banking.datamodel." + refactor.object());

		}

		final Map<String,String> mapFields=new HashMap<String,String>();
		final Map<String,Class> mapTypes=new HashMap<String,Class>();
		final List<String> list=new ArrayList<String>();
		
		
		for (int i = 0; i < object.getSuperclass().getDeclaredFields().length; i++) {
			
			if (object.getSuperclass().getDeclaredFields()[i].isAnnotationPresent(Field.class)) {
				
				Annotation annotation=object.getSuperclass().getDeclaredFields()[i].getAnnotation(Field.class);
				Field field=(Field)annotation;
				list.add(object.getSuperclass().getDeclaredFields()[i].getName());
				mapTypes.put(object.getSuperclass().getDeclaredFields()[i].getName(), object.getSuperclass().getDeclaredFields()[i].getType());
				mapFields.put(object.getSuperclass().getDeclaredFields()[i].getName(), field.name());
			}
				
		}
		
		
		for (int i = 0; i < object.getDeclaredFields().length; i++) {
			
			if (object.getDeclaredFields()[i].isAnnotationPresent(Field.class)) {
				
				Annotation annotation=object.getDeclaredFields()[i].getAnnotation(Field.class);
				Field field=(Field)annotation;
				list.add(object.getDeclaredFields()[i].getName());
				mapTypes.put(object.getDeclaredFields()[i].getName(), object.getSuperclass().getDeclaredFields()[i].getType());
				mapFields.put(object.getDeclaredFields()[i].getName(), field.name());
			}
			
		}
		
	
		
		for(int i=0; i<list.size(); i++) {
			
			System.out.println(list.get(i)+" "+mapFields.get(list.get(i)));
			
		}
		
		
		
		
		
		

		try {
			List<E> cities = this.jdbcTemplate.query("select * from city", new RowMapper<E>() {
				private E obj1;
				@Override
				public E mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					try {
						obj1=(E)object.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					for(int i=0; i<list.size(); i++) {
						try {
							java.lang.reflect.Field field=object.getDeclaredField(list.get(i));
							field.setAccessible(true);
							field.set(obj1, rs.getObject(mapFields.get(list.get(i))) );
						} catch (NoSuchFieldException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					return (E) obj1;

					
				}

			});
			return cities;
			
		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<E>(0);

		}
	}

	@Override
	public E getById(T id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(E entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public T insert(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
