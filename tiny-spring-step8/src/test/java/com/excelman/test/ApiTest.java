package com.excelman.test;

import com.excelman.context.support.ClassPathXmlApplicationContext;
import com.excelman.test.bean.UserService;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 上午10:48
 */
public class ApiTest {

    @Test
    public void test_Aware(){
        // 1. 创建BeanFactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 2. 注册钩子
        context.registerShutdownHook();

        // 3. 获取Bean，并输出测试结果
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
        System.out.println("ApplicationContext："+userService.getApplicationContext());
        System.out.println("BeanFactory："+userService.getBeanFactory());
    }
}
