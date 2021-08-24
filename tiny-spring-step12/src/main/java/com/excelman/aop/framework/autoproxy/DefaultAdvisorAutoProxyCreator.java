package com.excelman.aop.framework.autoproxy;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 上午9:30
 */

import com.excelman.aop.*;
import com.excelman.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.excelman.aop.framework.ProxyFactory;
import com.excelman.beans.BeansException;
import com.excelman.beans.factory.BeanFactory;
import com.excelman.beans.factory.BeanFactoryAware;
import com.excelman.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.excelman.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * 融入Bean生命周期的自动代理创建类
 *
 * 实现了两个接口，前者的作用是最终实现BeanPostProcessor接口，可放入Bean的生命周期；后者的作用是在创建Bean的时候，通过Aware感知，实现Aware对应的方法。
 * 主要职责：作为一个BeanPostProcessor以及Aware感知类，实现初始化之前的修改bean方法，获取bean对应的代理对象
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    /**
     * 实现于BeanFactoryAware接口的方法
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    /**
     * 实现BeanPostProcessor的方法
     * 从xml配置文件中获取所有的Advisors，遍历获取当前beanClass相应的Advisor，填充对应的Support信息，返回代理对象
     * 现在调用方获取到的Bean对象就是一个已经被切面注入的对象，当调用方法的时候，会被拦截器所拦截从而执行切面的通知方法
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 若是Advised等的其中一种类型，则直接返回null
        if(isInfrastructureClass(beanClass)) return null;

        // 1. 从beanFactory中获取Advisor包装bean
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        // 2. 遍历xml配置文件中所有的Advisors
        for(AspectJExpressionPointcutAdvisor advisor : advisors){
            // 3. 获取Advisor包装的AspectJ封装的ClassFilter，将beanClass与expression进行匹配
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if(!classFilter.matches(beanClass)) continue;

            // 4. 封装AdvisedSupport，使用代理工厂返回代理对象
            AdvisedSupport support = new AdvisedSupport();
            TargetSource targetSource = null;
            try{
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
            support.setTargetSource(targetSource);
            // 将advisor中包装的切面通知和方法匹配器提取包装到support中
            support.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            support.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            support.setProxyTargetClass(false); // 即使用JDK代理对象

            return new ProxyFactory(support).getProxy();
        }

        return null;
    }

    /**
     * 判断beanCLass是否属于Advice、pointcut、Advised中的其中一种类型
     */
    private boolean isInfrastructureClass(Class<?> beanClass){
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    /**
     * 以下两个来自于BeanPostProcessor的方法，直接返回bean对象，不做任何其他操作
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
