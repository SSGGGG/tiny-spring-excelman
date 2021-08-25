package com.excelman.beans.factory.config;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 上午9:31
 */

import com.excelman.beans.BeansException;

/**
 * Subinterface of {@link BeanPostProcessor} that adds a before-instantiation callback,
 * and a callback after instantiation but before explicit properties are set or
 * autowiring occurs.
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     *
     * 在目标 Bean 对象执行初始化方法之前，执行此方法
     * 返回的bean可能是目标bean的代理对象
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
