package dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午3:57
 */
public class UserDao {

    private static final Map<String,String> map = new HashMap<>();

    static {
        map.put("id1","id1_value");
    }

    public String queryUserName(String uid){
        return map.get(uid);
    }
}
