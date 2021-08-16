package com.excelman.beans.factory.config;

import com.excelman.beans.factory.BeanFactory;

/**
 * Extension of the {@link BeanFactory}
 * interface to be implemented by com.excelman.test.bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing com.excelman.test.bean instances.
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
}
