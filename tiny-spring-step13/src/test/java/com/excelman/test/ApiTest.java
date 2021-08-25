package com.excelman.test;

import com.excelman.context.annotation.ClassPathBeanDefinitionScanner;
import com.excelman.context.support.ClassPathXmlApplicationContext;
import com.excelman.test.bean.IUserService;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 下午9:21
 */
public class ApiTest {

    @Test
    public void test_scan_component(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        context.registerShutdownHook();

        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_scan_property(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        context.registerShutdownHook();

        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService);
    }

}
