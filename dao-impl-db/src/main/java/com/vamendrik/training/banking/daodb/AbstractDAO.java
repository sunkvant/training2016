package com.vamendrik.training.banking.daodb;

import java.util.List;

public interface AbstractDAO <E, T> {
	
	public List<E> getAll();
	public E getById(T id);
	public void update(E entity);
	public void deleteById(E entity);
	public void insert(E entity);
	
}
