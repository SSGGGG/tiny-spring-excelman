package com.excelman.beans.factory;

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.config.AutowireCapableBeanFactory;
import com.excelman.beans.factory.config.BeanDefinition;
import com.excelman.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 上午10:59
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
