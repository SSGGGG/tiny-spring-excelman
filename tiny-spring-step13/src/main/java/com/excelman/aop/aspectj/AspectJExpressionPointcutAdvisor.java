package com.excelman.aop.aspectj;

import com.excelman.aop.Pointcut;
import com.excelman.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 下午5:30
 */

/**
 * 实现PointcutAdvisor接口
 * 职责：将切面pointcut、拦截方法advice和具体的拦截表达式expression包装在一起，这样在xml配置中就可以定义一个pointcutAdvisor切面拦截器
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * 切面（被代理对象、匹配器、拦截器）]
     * 懒汉式单例创建
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 通知：具体的拦截执行方法
     * 由xml配置文件装配
     */
    private Advice advice;

    /**
     * 匹配表达式
     * 由xml配置文件装配
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        // 懒汉者——单例模式
        if(pointcut == null){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
