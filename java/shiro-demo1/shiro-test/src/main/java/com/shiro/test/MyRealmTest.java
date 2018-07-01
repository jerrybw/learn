package com.shiro.test;

import com.shiro.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class MyRealmTest {

    @Test
    public void testMyRealm(){
        MyRealm myRealm = new MyRealm();
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("jerry","123456"));
        System.out.println(subject.isAuthenticated());
    }
}
