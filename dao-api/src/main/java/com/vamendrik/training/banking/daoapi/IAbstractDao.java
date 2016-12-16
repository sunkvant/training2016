package com.vamendrik.training.banking.daoapi;

import java.util.List;

public interface IAbstractDao <E, T> {
	
	public List<E> getAll() throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException ;
	public E getById(T id);
	public void update(E entity);
	public void delete(E entity);
	public T insert(E entity);
	
}
