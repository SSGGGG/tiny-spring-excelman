package service;

import dao.UserDao;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午3:59
 */
public class UserService {

    private String uid;

    private UserDao userDao;

    // 这里的uid和userDao由我们的容器注入
    public void queryUserInfo(){
        System.out.println("查询用户信息:"+userDao.queryUserName(uid));
    }

}
