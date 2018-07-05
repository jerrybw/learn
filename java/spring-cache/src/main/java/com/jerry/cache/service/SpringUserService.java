package com.jerry.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jerry.cache.bean.User;

/**
 * @author 向博文
 * @date 2018年7月5日
 */
@Service
public class SpringUserService {
	
	@Cacheable(cacheNames = "users")
	public User getUserById(String userId) {
		System.out.println("真正查询user");
	}

}
