package com.excelman.test;

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.*;
import com.excelman.context.ApplicationContext;
import com.excelman.context.ApplicationContextAware;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/18 下午4:16
 */

/**
 * UserService实现各个Aware接口，从而在createBean的时候，通过instanceof判断通过而调用对应的响应方法
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {

    private UserDao userDao;
    private String uid;
    private String company;
    private String location;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader:"+classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanName:"+name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void queryUserInfo(){
        userDao.queryUserInfo(uid);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
