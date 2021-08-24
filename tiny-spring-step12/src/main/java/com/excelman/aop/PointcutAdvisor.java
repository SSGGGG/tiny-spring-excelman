package com.excelman.aop;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 下午5:28
 */
public interface PointcutAdvisor extends Advisor{

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();
}
