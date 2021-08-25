package com.excelman.beans.factory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 下午6:53
 */

import com.excelman.beans.BeansException;
import com.excelman.beans.PropertyValue;
import com.excelman.beans.PropertyValues;
import com.excelman.beans.factory.config.BeanDefinition;
import com.excelman.beans.factory.config.BeanFactoryPostProcessor;
import com.excelman.core.io.DefaultResourceLoader;
import com.excelman.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * 职责：处理占位符的配置，实现注解配置bean属性
 * 在创建Bean过程中，是先执行BeanFactoryPostProcessor，再createBean，所以在本类中先将配置文件的属性值读取到BeanDefinition的PropertyValues中
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 读取location指定的配置文件的配置信息包装到Properties类中，并读取BeanDefinition，将其中的所有${xxx}信息替换成Properties中配置信息
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        // 加载属性文件
        try{
            DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
            Resource resource = defaultResourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            // 获取beanDefinition的对应BeanName
            String[] beanDefinitionNames = factory.getBeanDefinitionNames();
            for(String beanName : beanDefinitionNames){
                BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
                // 获取该BeanDefinition的属性列表
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
                    Object value = propertyValue.getValue();
                    if(!(value instanceof String)) continue;
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);

                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if(startIdx!=-1 && stopIdx!=-1 && stopIdx>startIdx){
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIdx, stopIdx+1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
