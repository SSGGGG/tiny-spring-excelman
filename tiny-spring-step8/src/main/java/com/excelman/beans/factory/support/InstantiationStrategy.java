package com.excelman.beans.factory.support;

import com.excelman.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 上午10:39
 */
public interface InstantiationStrategy {

    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
