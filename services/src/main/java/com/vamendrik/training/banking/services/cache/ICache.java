package com.vamendrik.training.banking.services.cache;

import java.util.HashMap;
import java.util.HashSet;

public interface ICache {
	
	public void Cache();
	public Object get(Class key, Object columnData, String column);
	public void remove(Class key, Object columnData, String column);
	public void put(Class key, Object data, HashMap<String,Object> columns);
	
	

}
