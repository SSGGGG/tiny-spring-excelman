package com.excelman.aop;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 下午5:25
 */

import org.aopalliance.aop.Advice;

/**
 * 主要职责：
 * Advisor包装了pointcut和Advice，Pointcut用于获取JoinPoint，而Advice决定于JoinPoint执行什么操作
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
