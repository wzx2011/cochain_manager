package v1;

import org.junit.Test;
import util.TestOKHttp3V1;

/**
 * @program: cochain_manager
 * @description: 测试cochain-api项目下面的v1包中的第三方接口
 * @author: wzx
 * @create: 2019-07-09 11:34
 */
public class TokenTest {

    public static void main(String[] args) {
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        String token = null;
        try {
            token =  ok.token();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(token);
    }

    @Test
    public void test(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        String token = null;
        try {
            token =  ok.token();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(token);
    }
}
