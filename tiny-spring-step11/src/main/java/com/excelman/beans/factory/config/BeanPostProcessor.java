package com.excelman.beans.factory.config;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午3:17
 */

import com.excelman.beans.BeansException;

/**
 * Factory hook that allows for custom modification of new com.excelman.test.bean instances
 * e.g. checking for marker interfaces or wrapping them with proxies.
 */
public interface BeanPostProcessor {

    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
