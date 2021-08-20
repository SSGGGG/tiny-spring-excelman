package com.excelman.test;

import com.excelman.context.support.ClassPathXmlApplicationContext;
import com.excelman.test.event.CustomEvent;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 下午3:57
 */
public class ApiTest {

    @Test
    public void test_event(){
        // 1.创建context工厂
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        // 2.发布事件
        context.publishEvent(new CustomEvent(context,1111L, "测试发布事件"));
    }
}
