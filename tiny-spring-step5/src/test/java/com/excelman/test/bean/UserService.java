package com.excelman.test.bean;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 上午9:44
 */
public class UserService {

    private String uid;

    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println(userDao.queryUserName(uid));
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
}
