package com.excelman.beans.factory.support;

import com.excelman.beans.factory.config.BeanPostProcessor;
import com.excelman.beans.factory.config.ConfigurableBeanFactory;
import com.excelman.beans.BeansException;
import com.excelman.beans.factory.config.BeanDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:42
 */

/**
 * 职责如下：
 * 一、对BeanFactory接口的实现
 * 1. 首先获取beanName对应的单例bean
 * 2. 如果获取不到的话，先获取对应的BeanDefinition定义，然后再创建bean
 * (其中得到bean定义以及创建bean的方法，由继承的子类实现)
 * 二、存放BeanPostProcessor
 * 应用于创建bean的时候
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /** BeanPostProcessors to apply in createBean **/
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String beanName){
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        if(null != bean){
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name,beanDefinition,args);
    }

    /**
     * 各司其职：定义两个抽象方法，给下面继承的类实现
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }
}
