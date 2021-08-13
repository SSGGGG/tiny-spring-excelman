package com.excelman.beans.factory.config;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:27
 * 单例化注册的接口定义
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
