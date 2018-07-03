package com.jerry.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author 向博文
 * @date 2018年7月2日
 */
public class ShiroRealm extends AuthenticatingRealm {

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("firstRealm");
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		if("unknown".equals(username)) {
			throw new UnknownAccountException("用户名不存在！");
		}
		if("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定！");
		}
		Object principal = username;
		SimpleHash simpleHash = null;
		if("admin".equals(username)||"user".equals(username)) {
			simpleHash = new SimpleHash("MD5", "123456",username,1024);
		}
		Object credentials = simpleHash.toString();
		String realmName = getName();
		ByteSource bytes = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials,bytes,realmName);
		return info;
	}
	

}
