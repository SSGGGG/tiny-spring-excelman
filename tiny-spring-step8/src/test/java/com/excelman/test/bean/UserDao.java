package com.excelman.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/18 下午4:13
 */

/**
 * 这里的UserDao的初始化和销毁采用配置文件的方式
 */
public class UserDao {

    private static Map<String,String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行：UserDao的initData-method");
        hashMap.put("uid1","Excelman");
        hashMap.put("uid2","SGCODER");
    }

    public void destroyDataMethod(){
        System.out.println("执行：UserDao的destroy-method");
        hashMap.clear();
    }

    public void queryUserInfo(String uid){
        System.out.println(uid+"执行结果："+hashMap.get(uid));
    }
}
