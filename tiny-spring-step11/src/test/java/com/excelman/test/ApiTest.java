package com.excelman.test;

import com.excelman.aop.AdvisedSupport;
import com.excelman.aop.MethodMatcher;
import com.excelman.aop.TargetSource;
import com.excelman.aop.aspectj.AspectJExpressionPointcut;
import com.excelman.aop.framework.Cglib2AopProxy;
import com.excelman.aop.framework.JdkDynamicAopProxy;
import com.excelman.aop.framework.ReflectiveMethodInvocation;
import com.excelman.test.beans.IUserService;
import com.excelman.test.beans.UserService;
import com.excelman.test.beans.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午9:53
 */
public class ApiTest {

    /**
     * 不采用封装，采用jdk的代理方式，测试代理的方法
     */
    @Test
    public void test_proxy_method(){
        // 1. 创建目标对象
        Object target = new UserService();
        // 2. 创建目标对象的代理
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            // 3. 创建方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.excelman.test.beans.IUserService.*(..))");
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 若匹配的情况下，则通过方法拦截器
                if(methodMatcher.matches(method, target.getClass())){
                    // 创建方法拦截器(使用lambda表达式实现类，invocation就是方法的参数)
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try{
                            return invocation.proceed();
                        }finally {
                            System.out.println("监控 - Begin By Aop");
                            System.out.println("方法名称："+invocation.getMethod().getName());
                            System.out.println("方法耗时："+(System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End");
                        }
                    };
                    // 反射调用，参数为封装的一个MethodInvocation，对应上面lambda表达式的参数invocation
                    methodInterceptor.invoke(new ReflectiveMethodInvocation(target, method, args));
                }
                // 若不匹配，则直接执行反射方法
                return method.invoke(target, args);
            }
        });
        String result = proxy.queryUserInfo();
        System.out.println(result);
    }

    /**
     * 采用封装的形式，测试代理方法
     */
    @Test
    public void test_dynamic_proxy(){
        // 1. 创建代理的目标对象
        IUserService userService = new UserService();

        // 2. 创建切面包装类
        AdvisedSupport advised = new AdvisedSupport();
        advised.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.excelman.test.beans.IUserService.*(..))"));
        advised.setTargetSource(new TargetSource(userService));
        advised.setMethodInterceptor(new UserServiceInterceptor());

        // 3. 创建JDK的动态代理，获取切面包装类封装的被代理对象的代理
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advised).getProxy();
        // 执行代理对象的方法
        String result = proxy_jdk.queryUserInfo();
        System.out.println(result);

        // ===========================
        // 获取CGLIB的代理对象
        System.out.println("========================");
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advised).getProxy();
        System.out.println(proxy_cglib.register("excelman"));
    }

    /**
     * 测试方法表达式匹配
     */
    @Test
    public void test_method_match() throws NoSuchMethodException{
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.excelman.test.beans.IUserService.*(..))");
        // 通过反射获取UserService的方法
        Class<UserService> serviceClass = UserService.class;
        Method queryUserInfo = serviceClass.getDeclaredMethod("queryUserInfo");
        // 判断是否匹配
        System.out.println(pointcut.matches(serviceClass));
        System.out.println(pointcut.matches(queryUserInfo, serviceClass));
    }
}
