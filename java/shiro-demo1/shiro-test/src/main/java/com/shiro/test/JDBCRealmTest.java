package com.shiro.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JDBCRealmTest {

    DruidDataSource druidDataSource = new DruidDataSource();

    {
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:6666/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("159148");
    }

    @Test
    public void testJDBCRealm(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("jerry","123456"));
        System.out.println(subject.isAuthenticated());
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.isPermitted("user:delete"));
    }
}
