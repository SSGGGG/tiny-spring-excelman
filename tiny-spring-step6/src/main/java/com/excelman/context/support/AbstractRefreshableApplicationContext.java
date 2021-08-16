package com.excelman.context.support;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午4:28
 */

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.ConfigurableListableBeanFactory;
import com.excelman.beans.factory.support.DefaultListableBeanFactory;

/**
 * 职责：获取和创建Bean工厂
 * 以及实现刷新BeanFactory：首先创建BeanFactory，并调用`loadBeanDefinitions`对资源配置进行加载，在加载完成之后即可完成对Spring.xml配置
 * 文件中Bean对象的定义和注册（同时也包括了实现接口BeanFactoryPostProcessor和BeanPostProcessor）
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    /**
     * 依赖这个BeanFactory核心实现类（负责注册和获取bean的定义）
     */
    private DefaultListableBeanFactory beanFactory;


    // 实现抽象上下文实现类的两个方法

    /**
     * 刷新bean工厂的时候，还要重新从上下文中加载Bean的定义
     * @throws BeansException
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory); // 加载bean的定义
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    /**
     * 定义抽象方法，留给继承的子类实现
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
