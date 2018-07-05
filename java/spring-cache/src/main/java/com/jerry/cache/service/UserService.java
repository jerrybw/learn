package com.jerry.cache.service;

import com.jerry.cache.bean.User;
import com.jerry.cache.manager.CacheManager;

/**
 * @author 向博文
 * @date 2018年7月5日
 */
public class UserService {
	
	private CacheManager<User> cacheManager;
	
	public UserService() {
		cacheManager = new CacheManager<User>();
	}
	
	public User getUserByUserId(String userId) {
		User res = cacheManager.getValue(userId);
		if(res != null) {
			System.out.println("命中！");
			return res;
		}
		res = getFromDB(userId);
		if(res != null) {
			System.out.println("未命中！从数据库中查出。");
			cacheManager.addOrUpdateValue(userId, res);
			return res;
		}
		return res;
	}

	
	public void reload() {
		cacheManager.evictCache();
	}
	
	/**
	 * @param userId
	 * @return
	 */
	private User getFromDB(String userId) {
		// TODO Auto-generated method stub
		return new User("1", "1", 1);
	}
	
	

}
