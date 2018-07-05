package com.jerry.cache.test;

import com.jerry.cache.service.UserService;

/**
 * @author 向博文
 * @date 2018年7月5日
 */
public class UserCacheTest {
	public static void main(String[] args) {
		UserService userService = new UserService();
		System.out.println("第一次查询");
		userService.getUserByUserId("1");
		System.out.println("第二次查询");
		userService.getUserByUserId("1");
		System.out.println("reload");
		userService.reload();
		System.out.println("reload之后第一次查询");
		userService.getUserByUserId("1");
		System.out.println("reload之后第二次查询");
		userService.getUserByUserId("1");
	}
}
