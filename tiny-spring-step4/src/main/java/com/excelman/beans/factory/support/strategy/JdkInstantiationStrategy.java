package com.excelman.beans.factory.support.strategy;

import config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午7:10
 * 采用JDK的实例化
 */
public class JdkInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        // 1.首先获取bean对应的class
        Class clazz = beanDefinition.getBeanClass();
        Object result = null;
        try{
            // 2.判断构造器是否为空，若为空，则直接实例化
            if(null == ctor){
                result = clazz.getDeclaredConstructor().newInstance();
            }else{
                result = clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return result;
    }
}
