package v1;

import net.sf.json.JSONObject;
import org.junit.Test;
import util.TestOKHttp3V1;

/**
 * @program: cochain_manager
 * @description: 测试cochain-api项目下面的v1包中的第三方接口
 * @author: wzx
 * @create: 2019-07-09 11:33
 */
public class JifenTest {

    @Test
    public void getJifen(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 用户名
        String username = "34173CB38F07F89DDBEBC2AC9128303F";
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.getJifen(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getJifen返回的数据："+resultData);
    }

    @Test
    public void getJifenHistory(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 用户名
        String username = "34173CB38F07F89DDBEBC2AC9128303F";
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.getJifenHistory(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getJifenHistory返回的数据："+resultData);
    }

    @Test
    public void goBlockChain(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 用户名
        String artifactId = "10c1d75390c718173bbb4086fab31c2a8ee87a99e2fe6cb99b843c57431ce21b";
        try {
            ok.goBlockChain(artifactId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
