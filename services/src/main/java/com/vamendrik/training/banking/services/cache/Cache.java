package com.vamendrik.training.banking.services.cache;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Cache implements ICache {

	private ConcurrentHashMap<Class, ConcurrentHashMap<Object, Object>> globalMap = new ConcurrentHashMap<Class, ConcurrentHashMap<Object, Object>>();
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
	public void put(Class key, Object data, String column) {


		if (globalMap.get(key) == null) {

			ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<Object, Object>();
			map.put(column, data);
			globalMap.put(key, map);

		} else {

			ConcurrentHashMap<Object, Object> map=globalMap.get(key);
			map.put(column, data);

		}

	}

	@Override
	public Object get(Class key, Object columnData, String column) {

		if (globalMap.get(key) == null) {

			ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<Object, Object>();
			globalMap.put(key, map);

		} else {
			ConcurrentHashMap<Object, Object> map = globalMap.get(key);

					if (map.get(column) != null) {

						return map.get(column);

					}

		}
		return  null;

	}

}
