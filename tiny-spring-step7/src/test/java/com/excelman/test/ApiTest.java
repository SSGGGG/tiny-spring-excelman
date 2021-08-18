package com.excelman.test;

import com.excelman.context.support.ClassPathXmlApplicationContext;
import com.excelman.test.bean.UserService;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/18 下午4:13
 */
public class ApiTest {

    @Test
    public void test_xml(){
        // 1. 初始化BeanFactory，并且注册虚拟机钩子
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 在context中注册钩子，当注销bean的时候，钩子会调用context的close方法，在close方法中，通过获取BeanFactory从而调用bean工厂的destroySingletons()方法
        context.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
