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

	final List<String> fieldsFromClass = AnnotationAnalizator.getFields();
	final Map<String, String> mapFieldsFromClass = AnnotationAnalizator.getMapFields();

	final List<String> fieldsFromSuperclass = AnnotationAnalizator.getFieldsSuperClass();
	final Map<String, String> mapFieldsFromSuperClass = AnnotationAnalizator.getMapFieldsSuperClass();
	
	private Class object;
	private String tableName;

	@Override
	public List<E> getAll() throws ClassNotFoundException, NoSuchFieldException, SecurityException,
			InstantiationException, IllegalAccessException {

		this.object = AnnotationAnalizator.initialize(this.getClass());
		this.tableName=AnnotationAnalizator.getTableName();

		try {
			List<E> cities = this.jdbcTemplate.query("select * from city", new RowMapper<E>() {
				private E instance;

				@Override
				public E mapRow(ResultSet rs, int rowNum) throws SQLException {

					try {
						instance = (E) object.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {

						e.printStackTrace();
					}

					try {
						for (int i = 0; i < fieldsFromClass.size(); i++) {

							java.lang.reflect.Field field;

							field = object.getDeclaredField(fieldsFromClass.get(i));

							field.setAccessible(true);
							field.set(instance, rs.getObject(mapFieldsFromClass.get(fieldsFromClass.get(i))));
						}

						for (int i = 0; i < fieldsFromSuperclass.size(); i++) {

							java.lang.reflect.Field field = object.getSuperclass()
									.getDeclaredField(fieldsFromSuperclass.get(i));
							field.setAccessible(true);
							field.set(instance, rs.getObject(mapFieldsFromSuperClass.get(fieldsFromSuperclass.get(i))));
						}

						return (E) instance;

					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
							| IllegalAccessException e) {
						return null;
						//e.printStackTrace();
					}

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
