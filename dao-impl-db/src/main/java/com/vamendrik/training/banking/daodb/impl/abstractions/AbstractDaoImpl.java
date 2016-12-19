package com.vamendrik.training.banking.daodb.impl.abstractions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.vamendrik.training.banking.daoapi.IAbstractDao;

public abstract class AbstractDaoImpl<E, T> implements IAbstractDao<E, T> {

	@Inject
	protected JdbcTemplate jdbcTemplate;
	
	@Inject
	private IAnnotationAnalizator annotationAnalizator;
	
	private Class object;
	private String tableName;
	
	private String generateInsertSQL() {
		
		List<String> fields = annotationAnalizator.getFields();
		Map<String,String> mapFields= annotationAnalizator.getMapFields();
		mapFields.remove("id");
		fields.remove("id");
		
		
		StringBuilder stringBuilder=new StringBuilder();
		
		stringBuilder.append("insert into ");
		stringBuilder.append(annotationAnalizator.getTableName());
		
		stringBuilder.append(" (");
		
		for(int i=0; i<fields.size(); i++) {
			
			stringBuilder.append(mapFields.get(fields.get(i)));
			
			if (i<fields.size()-1)
				stringBuilder.append(",");
			
		}
		stringBuilder.append(") values (");
		
		for(int i=0; i<fields.size(); i++) {
			
			stringBuilder.append("?");
			
			if (i<fields.size()-1)
				stringBuilder.append(",");
			
		}
		
		stringBuilder.append(")");
		
		return stringBuilder.toString();
		
		
		
	}
	
	private String generateUpdateSQL() {
		
		List<String> fields = annotationAnalizator.getFields();
		Map<String,String> mapFields= annotationAnalizator.getMapFields();
		mapFields.remove("id");
		fields.remove("id");
		
		
		StringBuilder stringBuilder=new StringBuilder();
		
		stringBuilder.append("update ");
		stringBuilder.append(annotationAnalizator.getTableName());
		
		stringBuilder.append(" set ");
		
		for(int i=0; i<fields.size(); i++) {
			
			stringBuilder.append(mapFields.get(fields.get(i)));
			stringBuilder.append("=?");
			
			if (i<fields.size()-1)
				stringBuilder.append(",");
			
		}
		stringBuilder.append(" where id=?");
		
		return stringBuilder.toString();
		
		
		
	}

	
	private void ContextInitializtion() {
		try {
			
			this.object = annotationAnalizator.initialize(this.getClass());
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		
		this.tableName=annotationAnalizator.getTableName();
	}
	
	protected List<E> getAllByFieldValue(String SQL,Object[] args)  {

		ContextInitializtion();

		try {
			List<E> customs = this.jdbcTemplate.query(SQL, args, new AbstractMapper<E>(this.object,annotationAnalizator));
			return customs;

		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<E>(0);

		}
	}
	
	protected E getByFieldValue(String SQL,Object[] args)  {

		ContextInitializtion();

		try {
			E custom = this.jdbcTemplate.queryForObject(SQL, args, new AbstractMapper<E>(this.object,annotationAnalizator));
			return custom;

		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}
	
	
	
	@Override
	public List<E> getAll()  {

		ContextInitializtion();

		try {
			List<E> cities = this.jdbcTemplate.query("select * from "+tableName+" where is_delete=false order by id asc", new AbstractMapper<E>(this.object,annotationAnalizator));
			return cities;

		} catch (EmptyResultDataAccessException e) {

			return new ArrayList<E>(0);

		}
	}



	@Override
	public E getById(T id) {
		ContextInitializtion();
		
		try {
			E city = this.jdbcTemplate.queryForObject("select * from "+tableName+" where id=?", new Object[] { id },
					new AbstractMapper<E>(this.object,annotationAnalizator));
			return city;
		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	@Override
	public void update(E entity) {
	ContextInitializtion();
		

		System.out.println(generateUpdateSQL());
		

		jdbcTemplate.update(new AbstractPreparedStatementCreator<E>(generateUpdateSQL(), this.object, entity,annotationAnalizator,false));

	}


	@Override
	public T insert(E entity) {
		ContextInitializtion();
		

		System.out.println(generateInsertSQL());
		

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new AbstractPreparedStatementCreator<E>(generateInsertSQL(), this.object, entity,annotationAnalizator,true), keyHolder);

		return (T) keyHolder.getKey();
		
		
	}

}
