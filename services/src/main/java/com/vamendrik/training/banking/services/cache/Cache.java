package com.vamendrik.training.banking.services.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Cache implements ICache {

	private ConcurrentHashMap<Class, ConcurrentHashMap<HashMap<String, Object>, Object>> globalMap = new ConcurrentHashMap<Class, ConcurrentHashMap<HashMap<String, Object>, Object>>();
	private Long check;

	private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			Thread th = new Thread(r);
			th.setDaemon(true);
			return th;
		}
	});

	public void Cache() {

		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				globalMap.clear();
			}
		}, 1, 500000, TimeUnit.MILLISECONDS);

	}
	
	@Override
	public void remove(Class key, Object columnData, String column) {

		if (globalMap.get(key) == null) {

			return;

		} else {
			ConcurrentHashMap<HashMap<String, Object>, Object> map = globalMap.get(key);

			Set<HashMap<String, Object>> setmap = map.keySet();

			HashMap<String, Object> bufmap;

			while (setmap.iterator().hasNext()) {

				bufmap = setmap.iterator().next();

				Object obj = bufmap.get(column);

				if (obj.equals(columnData)) {

					map.remove(bufmap);
				}

			}

		}

	}

	@Override
	public void put(Class key, Object data, HashMap<String, Object> columns) {

		if (globalMap.get(key) == null) {

			ConcurrentHashMap<HashMap<String, Object>, Object> map = new ConcurrentHashMap<HashMap<String, Object>, Object>();
			map.put(columns, data);
			globalMap.put(key, map);

		} else {

			ConcurrentHashMap<HashMap<String, Object>, Object> map = globalMap.get(key);
			map.put(columns, data);

		}

	}

	@Override
	public Object get(Class key, Object columnData, String column) {

		if (globalMap.get(key) == null) {

			return null;

		} else {
			ConcurrentHashMap<HashMap<String, Object>, Object> map = globalMap.get(key);

			Set<HashMap<String, Object>> setmap = map.keySet();

			HashMap<String, Object> bufmap;

			while (setmap.iterator().hasNext()) {

				bufmap = setmap.iterator().next();

				Object obj = bufmap.get(column);

				if (obj.equals(columnData)) {

					return map.get(bufmap);
				}

			}

		}
		return null;

	}

}
