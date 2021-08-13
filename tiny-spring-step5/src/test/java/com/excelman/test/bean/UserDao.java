package com.excelman.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 上午9:43
 */
public class UserDao {

    private static Map<String,String> map = new HashMap<>();

    static {
        map.put("uid1","uid1的结果");
        map.put("uid2","uid2的结果");
    }

    public String queryUserName(String uid){
        return map.get(uid);
    }

}
