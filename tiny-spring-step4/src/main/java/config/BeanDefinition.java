package config;

import bean.PropertyValues;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:27
 * @description bean的定义
 */
public class BeanDefinition {

    /**
     * 这里采用Class来定义一个对象，这样的话就可以把bean的实例化操作放到容器中处理
     */
    private Class beanClass;

    /**
     * 这里因为要将属性也交给bean的定义，因此填充了这个属性
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass,PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
