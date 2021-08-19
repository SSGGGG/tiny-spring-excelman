package com.excelman.beans.factory.support;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/17 上午10:58
 */

import cn.hutool.core.util.StrUtil;
import com.excelman.beans.BeansException;
import com.excelman.beans.factory.DisposableBean;
import com.excelman.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 定义销毁方法的适配器
 * 适配器类的作用：因为销毁方法有多种实现方式，在销毁执行的时候不希望还得关注所有销毁类型的方法
 * 在使用上更希望有一个统一的接口进行销毁，所以就增加了适配器类，做统一的处理
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        // 这里为设置bean定义中的destroyMethod
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    /**
     * 销毁的方法
     */
    @Override
    public void destroy() throws Exception {
        // 1. 实现接口的方式
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }

        // 2. xml配置文件定义的方式
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))){
            Method method = bean.getClass().getMethod(destroyMethodName);
            if(null == method){
                throw new BeansException("Could not find a destroy method");
            }
            method.invoke(bean);
        }
    }
}
