package v1;

import net.sf.json.JSONObject;
import org.junit.Test;
import util.TestOKHttp3V1;

/**
 * @program: cochain_manager
 * @description: 测试cochain-api项目下面的v1包中的第三方接口
 * @author: wzx
 * @create: 2019-07-09 11:34
 */
public class UserTest {

    public static void main(String[] args) {
        // 参数
        String token = "0c449b50d9de93b72a3827806be514dbe84aa71570326646428b6d96cd45ade6";
        String role = "1";
        String secretkey = "452136";

        TestOKHttp3V1 ok = new TestOKHttp3V1();
        String userData = null;
        try {
            userData =  ok.createUser(token,role,secretkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userData);
    }

    @Test
    public void test(){
        // 参数
        String token = "1246fc7a80d79c9d7b3c0407b54793dac6b18c2466ef0c38559a8aea8a61725e";
        String role = "1";
        String secretkey = "457892";

        TestOKHttp3V1 ok = new TestOKHttp3V1();
        String userData = null;
        try {
            userData =  ok.createUser(token,role,secretkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userData);
    }
}
