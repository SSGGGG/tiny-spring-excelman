package com.excelman.test;

import com.excelman.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 下午5:38
 */
public class ApiTest {

    @Test
    public void test_prototype(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        // 2.获取Bean对象
        UserService userService = context.getBean("userService", UserService.class);
        UserService userService1 = context.getBean("userService",UserService.class);

        // 3.判断是否相同
        System.out.println("userService:"+userService);
        System.out.println("userService1:"+userService1);
        System.out.println("是否相同："+ (userService == userService1));
    }

    @Test
    public void test_factory_bean(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        // 2.获取对象
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
