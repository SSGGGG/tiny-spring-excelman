package com.excelman.beans.factory.config;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午3:15
 */

import com.excelman.beans.factory.ConfigurableListableBeanFactory;
import com.excelman.beans.BeansException;

/**
 * Allows for custom modification of an application context's com.excelman.test.bean definitions,
 * adapting the com.excelman.test.bean property values of the context's underlying com.excelman.test.bean factory.
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完场后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     * @param factory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException;
}
