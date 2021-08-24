package com.excelman.test;

import com.excelman.context.support.ClassPathXmlApplicationContext;
import com.excelman.test.beans.IUserService;
import com.excelman.test.beans.UserService;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午9:53
 */
public class ApiTest {

    @Test
    public void test_spring_aop(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println("测试结果" + userService.queryUserInfo());
    }
}
