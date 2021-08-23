package com.excelman.beans.factory.support;

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.FactoryBean;
import com.excelman.beans.factory.config.BeanDefinition;
import com.excelman.beans.factory.config.BeanPostProcessor;
import com.excelman.beans.factory.config.ConfigurableBeanFactory;
import com.excelman.util.ClassUtils;

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
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /** ClassLoader to resolve bean class names with, if necessary */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

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

    /**
     * step9修改：添加了调用getObjectForBeanInstance方法获取FactoryBean的对象
     * createBean的具体实现还是交由继承类来实现，而FactoryBean的object获取则放在该类中实现
     * 先执行createBean创建bean，最后返回bean的时候调用getObjectForBeanInstance()方法判断是否是FactoryBean
     */
    protected <T> T doGetBean(final String name, final Object[] args){
        Object sharedInstance = getSingleton(name);
        if(null != sharedInstance){
            // 如果是FactoryBean，则需要调用FactoryBean#getObject方法获取封装的object对象
            return (T) getObjectForBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }
    /**
     * 若是FactoryBean的话，则先从缓存中获取对象，若不存在则执行getObjectFromFactoryBean方法获取对象
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName){
        if(!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if(object == null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
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

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }
}
