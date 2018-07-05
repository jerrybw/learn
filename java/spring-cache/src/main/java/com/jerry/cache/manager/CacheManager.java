package com.jerry.cache.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 向博文
 * @date 2018年7月5日
 */
public class CacheManager<T> {

	private Map<String, T> cache = new ConcurrentHashMap<>();
	
	public T getValue(Object key) {
		return cache.get(key);
	}
	
	public void addOrUpdateValue(String key,T value) {
		cache.put(key, value);
	}
	
	public void evictCache(String key) {
		cache.remove(key);
	}
	
	public void evictCache() {
		cache.clear();
	}
}
