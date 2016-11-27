package com.vamendrik.training.banking.services;

import java.util.List;


public interface IAbstractService<E,T> {
	
	public List<E> getAll();
	public void delete(E entity);
	public void save(E entity);
	public E get(T id);
	

}
