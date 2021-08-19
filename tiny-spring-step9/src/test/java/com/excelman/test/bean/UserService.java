package com.excelman.test.bean;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 下午5:41
 */
public class UserService {

    private String uid;
    private String company;
    private String location;
    /**
     * 这个修改为IUserDao类型
     */
    private IUserDao userDao;

    public void queryUserInfo(){
        System.out.println(userDao.queryUserName(uid)+","+company+","+location);
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

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
