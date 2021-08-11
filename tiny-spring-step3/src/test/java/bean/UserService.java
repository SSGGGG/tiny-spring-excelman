package bean;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午7:36
 */
public class UserService {

    private String testName;

    public UserService(String testName){
        this.testName = testName;
    }

    public void queryInfo(){
        System.out.println(testName+":正在查询用户信息");
    }
}
