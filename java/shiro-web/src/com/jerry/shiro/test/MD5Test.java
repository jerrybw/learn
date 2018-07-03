package com.jerry.shiro.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * @author 向博文
 * @date 2018年7月3日
 */
public class MD5Test {

	@Test
	public void testMD5() {
		SimpleHash simpleHash = new SimpleHash("MD5", "123456",1024);
		System.out.println(simpleHash);
	}
}
