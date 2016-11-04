package com.vamendrik.training.banking.daodb;

import java.util.List;

public interface AbstractDao <E, T> {
	
	public List<E> getAll();
	public E getById(T id);
	public void update(E entity);
	public void delete(E entity);
	public T insert(E entity);
	
}
