package com.vamendrik.training.banking.services.cache;

public interface ICache<K,V> {
	
	public void Cache();
	void put(Class key, Object data, String column);
	Object get(Class key, Object data, String column);
	
	

}
