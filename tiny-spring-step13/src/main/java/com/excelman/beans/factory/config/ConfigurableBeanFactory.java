package com.excelman.beans.factory.config;

import com.excelman.beans.factory.BeanFactory;
import com.excelman.beans.factory.HierarchicalBeanFactory;

/**
 * Configuration interface to be implemented by most com.excelman.test.bean factories. Provides
 * facilities to configure a com.excelman.test.bean factory, in addition to the com.excelman.test.bean factory
 * client methods in the {@link BeanFactory}
 * interface.
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 定义摧毁单例的方法，并由AbstractBeanFactory实现类的父类实现
     */
    void destroySingletons();
}
