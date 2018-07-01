package com.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class TestShiro {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("jerry","123456");
    }

    @Test
    public void testAuth(){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        defaultSecurityManager.setRealm(simpleAccountRealm);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("jerry","1234567");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        subject.logout();
        System.out.println(subject.isAuthenticated());
    }
}
