package com.excelman.test.beans;

import com.excelman.aop.BeforeAdvice;
import com.excelman.aop.MethodBeforeAdvice;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 上午11:03
 */

/**
 * 自定义拦截方法
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法的前置通知："+method.getName());
    }
}
