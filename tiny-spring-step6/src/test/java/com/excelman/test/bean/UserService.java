package com.excelman.test.bean;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/16 上午10:14
 */
public class UserService {

    private String uid;
    private UserDao userDao;
    // 新增两个属性，用于测试新增的两个扩展接口对bean属性扩展的作用
    private String location;
    private String company;

    public void queryUserInfo(){
        System.out.println(userDao.queryUserName(uid)+","+location+","+company);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
