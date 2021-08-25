package com.excelman.aop.framework;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 下午2:38
 */

import com.excelman.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB实现的代理类
 */
public class Cglib2AopProxy implements AopProxy{

    /**
     * 注入切面包装类
     */
    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 获取advised包装的目标对象的代理
     */
    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedIntercepor(advised));
        return enhancer.create();
    }

    /**
     * CGLIB的Enhancer的callback方法参数，作为扩展进去的用户拦截方法
     * （注意！！！这个方法拦截器是CGLIB包下的方法拦截器，作为CGLIB的callback扩展）
     * （切面包装类里面所包装的拦截器是aop包下的拦截器）
     */
    private static class DynamicAdvisedIntercepor implements MethodInterceptor{

        private final AdvisedSupport advised;

        private DynamicAdvisedIntercepor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            // 首先创建MethodInterceptor的对象
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy);
            // 判断是否匹配
            if(advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())){
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            // 若不匹配，则直接执行反射方法返回
            return methodInvocation.proceed();
        }
    }

    /**
     * MethodInterceptor的一个实现类，作为方法拦截器的方法参数
     */
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation{

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
