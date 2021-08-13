package com.excelman.beans.factory.support;

import com.excelman.beans.factory.config.BeanDefinition;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午4:12
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断是否包含指定名称的BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);
}
