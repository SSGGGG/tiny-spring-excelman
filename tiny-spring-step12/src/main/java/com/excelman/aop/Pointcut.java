package com.excelman.aop;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午10:08
 */


/**
 * AOP切入点的接口定义
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut.
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
