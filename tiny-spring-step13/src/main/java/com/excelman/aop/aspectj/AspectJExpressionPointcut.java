package com.excelman.aop.aspectj;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午10:20
 */

import com.excelman.aop.ClassFilter;
import com.excelman.aop.MethodMatcher;
import com.excelman.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 切点表达式
 * 职责：实现Pointcut、ClassFilter、MethodMatcher三个接口的定义方法，同时该类对aspectj包提供的表达式校验方法使用
 */
public class AspectJExpressionPointcut implements ClassFilter, MethodMatcher, Pointcut {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static{
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcurExpression;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcurExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 实现ClassFilter的匹配方法
     * @param clazz the candidate target class
     * @return
     */
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcurExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * 实现MethodMatcher的匹配方法
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcurExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
