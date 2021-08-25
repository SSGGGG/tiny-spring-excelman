package com.excelman.beans.factory.support;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 下午4:01
 */

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FactoryBean注册的实现类
 * 职责：负责FactoryBean -> Object的管理
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     * 注意：若Object为null的话，存在缓存中的对象是NULL_OBJECT
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    /**
     * 得到缓存的对象
     */
    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    /**
     * 因为又有缓存获取对象，又有直接获取对象的方式，因此，额外提供这个方法进行逻辑包装
     * 这里的开发逻辑跟Redis以及数据库的逻辑很像
     * 先从缓存中获取对象，若不存在，则直接获取对象，并存放到缓存中
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory,String beanName){
        // 若是单例的话，记得放到缓存当中
        if(factory.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if(object == null){
                object = doGetObjectFromFactoryBean(factory,beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        }else{
            return doGetObjectFromFactoryBean(factory,beanName);
        }
    }

    /**
     * 直接获取对象
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try{
            return factory.getObject();
        }catch (Exception e){
            throw new BeansException("FactoryBean threw exception on object");
        }
    }
}
