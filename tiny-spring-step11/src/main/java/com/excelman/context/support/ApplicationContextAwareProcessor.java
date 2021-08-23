package com.excelman.context.support;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 上午10:07
 */

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.config.BeanPostProcessor;
import com.excelman.context.ApplicationContext;
import com.excelman.context.ApplicationContextAware;

/**
 * 包装处理器
 * 包装的目的是：由于ApplicationContext的获取不能直接在创建bean的时候就可以拿到，所以需要在refresh操作的时候，把ApplicationContext写入到一个包装的BeanPostProcessor中
 * 再由AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization()方法调用
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // !!!!
        // 因为ApplicationContext只能在context包下获取，因此这个Aware存在在context包中
        // 想要在createBean的时候能调用，只能采取实现BeanPostProcessor的方式，从而作为BeanPostProcessor存放到BeanFactory中
        // 这里注意要判断Bean的类型是否是Aware，若是的话再调用对应的方法
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
