package com.excelman.aop.framework;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午11:26
 */

import com.excelman.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK实现的代理类
 * 实现AopProxy和InvocationHandler接口，把获取代理对象getProxy()和反射调用方法invoke()分开实现
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    /**
     * 注入切面包装类（包装了代理目标对象、拦截器、匹配器）
     */
    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 获得advised包装的目标代理对象的代理
     */
    @Override
    public Object getProxy() {
        // 最后一个参数是this，因为当前类实现了InvocationHandler接口
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    /**
     * 代理的反射方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())){
            // 执行拦截器反射调用
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        // 若不匹配，则直接执行返回
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }
}
