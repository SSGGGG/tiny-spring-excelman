package com.excelman.aop.framework.adapter;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 下午5:49
 */

import com.excelman.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 方法拦截器的实现
 * 职责：invoke方法中调用依赖的advice.before前置通知方法
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    /**
     * 依赖前置通知，即拦截方法
     */
    private MethodBeforeAdvice advice;

    /**
     * 千万要注意啊！！！若存在非空参数的构造函数的话，记得要添加一个空参数的构造函数！！！
     * 在createBean的时候，需要用到，因为先实例化bean，再添加属性
     */
    public MethodBeforeAdviceInterceptor(){
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 先执行前置通知的before()方法
        this.advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        // 再执行invocation.proceed()方法
        return invocation.proceed();
    }

}
