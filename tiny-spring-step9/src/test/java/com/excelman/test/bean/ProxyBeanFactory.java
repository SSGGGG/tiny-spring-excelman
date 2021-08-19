package com.excelman.test.bean;

import com.excelman.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 下午5:43
 */

/**
 * 在xml配置文件中，将以前的UserDao配置Bean，转换成现在的代理配置Bean
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    /**
     * 代理类替换以前的UserDao，在getBean的时候会调用该方法，获取实际代理的Object
     * todo 动态代理，待学习！！！！
     * @return
     * @throws Exception
     */
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("uid1","excelman");
            hashMap.put("uid2","SGCODER");

            return "你被代理了 "+ method.getName() + ":" + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
