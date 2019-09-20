package v1;

import net.sf.json.JSONObject;
import org.junit.Test;
import util.TestOKHttp3V1;

/**
 * @program: cochain_manager
 * @description: 获取digtxhash测试
 * @author: wzx
 * @create: 2019-08-23 17:18
 */
public class TxhashTest {

    @Test
    public void getTxhash(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 二维码后缀
        String artifact_id = "0x9d731e722fb39d6d512179e2ef14eac51152403248fd8939888c73e0bbc560e9";
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.getTxhash(artifact_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getTxhash返回的数据："+resultData);
    }

    @Test
    public void getTxhash1(){

        String artifact_id = "ccad8f7ffc6468cf2bfc51ced633ea24931f121c";
        String url = "https://ib.vechain.com/artifact-api/v1/artifacts/txids/artifact_id";
        url = url.replace("artifact_id",artifact_id);

        TestOKHttp3V1 okhttp3Util = new TestOKHttp3V1();
        try {
            JSONObject resultData =  okhttp3Util.getTxhash1(url);
            System.out.println(resultData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
