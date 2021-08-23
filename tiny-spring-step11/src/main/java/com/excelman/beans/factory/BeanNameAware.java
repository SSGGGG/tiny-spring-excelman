package com.excelman.beans.factory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 上午9:59
 */

/**
 * Interface to be implemented by beans that want to be aware of their bean name in a bean factory.
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);
}
