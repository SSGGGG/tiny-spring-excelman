package com.excelman.test.common;

import com.excelman.beans.factory.ConfigurableListableBeanFactory;
import com.excelman.beans.factory.config.BeanFactoryPostProcessor;
import com.excelman.beans.BeansException;
import com.excelman.beans.PropertyValue;
import com.excelman.beans.PropertyValues;
import com.excelman.beans.factory.config.BeanDefinition;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/16 上午10:18
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        // 首先获取bean的定义
        BeanDefinition userService = factory.getBeanDefinition("userService");
        // 紧着获取bean定义的属性值
        PropertyValues propertyValues = userService.getPropertyValues();
        // 添加新的属性值
        propertyValues.addPropertyValue(new PropertyValue("company","改为：SENSETIME"));
    }
}
