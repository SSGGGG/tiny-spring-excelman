package com.excelman.aop;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午10:10
 */

/**
 * AOP类过滤器的接口定义
 * 用途：用于切点找到给定的接口和目标类
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}
