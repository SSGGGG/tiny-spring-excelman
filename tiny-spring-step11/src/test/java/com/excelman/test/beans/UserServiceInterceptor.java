package com.excelman.test.beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 下午3:27
 */

/**
 * 用户自定义的拦截器
 * 实现invoke方法，在该方法中，对invocation.proceed()方法放行，并在finally中添加监控信息
 */
public class UserServiceInterceptor implements MethodInterceptor {
    /**
     * 这里的invocation是封装了support信息的MethodInvocation实现类
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            return invocation.proceed();
        }finally {
            System.out.println("监控 - Begin By Aop");
            System.out.println("方法名称：" + invocation.getMethod().getName());
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End");
        }
    }
}
