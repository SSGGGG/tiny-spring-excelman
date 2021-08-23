package com.excelman.aop;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午10:52
 */

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 包装切面通知信息
 * 职责：主要是把代理对象、拦截、匹配的各项属性包装到一个类中，方便在Proxy实现类进行使用
 * （类似业务开发中的包装入参）
 */
public class AdvisedSupport {

    /**
     * 被代理的目标对象：在目标对象类中获取Object本身以及目标类TargetClass信息
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器：一个具体拦截方法实现类，由用户自己实现MethodInterceptor#invoke方法，做具体的实现
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器（检查目标方法是否符合通知条件）：一个匹配的操作，具体由AspectJExpressionPointcut提供服务
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
