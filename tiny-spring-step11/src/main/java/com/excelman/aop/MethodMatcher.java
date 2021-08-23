package com.excelman.aop;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午10:10
 */

import java.lang.reflect.Method;

/**
 * AOP方法匹配器的接口定义
 * 用途：方法匹配，找到表达式范围内匹配下的目标类和方法
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
