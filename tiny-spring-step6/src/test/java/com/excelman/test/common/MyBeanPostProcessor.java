package com.excelman.test.common;

import com.excelman.beans.factory.config.BeanPostProcessor;
import com.excelman.test.UserService;
import com.excelman.beans.BeansException;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/16 上午10:20
 */
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            System.out.println("这里是postProcessBeforeInitialization");
            UserService userService = (UserService) bean;
            userService.setLocation("改为： 深圳");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
