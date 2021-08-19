package com.excelman.test;

import com.excelman.beans.factory.DisposableBean;
import com.excelman.beans.factory.InitializingBean;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/18 下午4:16
 */

/**
 * 这里的UserService的初始化和销毁采用实现接口的方式
 */
public class UserService implements InitializingBean, DisposableBean {

    private UserDao userDao;
    private String uid;
    private String company;
    private String location;

    /**
     * 销毁的方法
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService的destroy()方法");
    }

    /**
     * 初始化之后，在属性设置之后执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService的afterPropertiesSet()方法");
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
}
