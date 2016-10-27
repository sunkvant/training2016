package com.vamendrik.training.banking.daodb;

import java.util.List;

public interface AbstractDAO <E, T> {
	
	public List<E> getAll();
	public E getById(T id);
	public void update(E entity);
	public boolean deleteById(T id);
	public boolean insert(E entity);
	
}
