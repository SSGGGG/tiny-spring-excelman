package com.excelman.beans.factory;

import com.excelman.beans.BeansException;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:42
 */
public interface BeanFactory {

    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
