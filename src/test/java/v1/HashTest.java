package v1;

import com.manage.util.GetContentUtil;
import net.sf.json.JSONObject;
import org.junit.Test;
import util.TestOKHttp3V1;

/**
 * @program: cochain_manager
 * @description: 测试cochain-api项目下面的v1包中的第三方接口
 * @author: wzx
 * @create: 2019-07-09 11:32
 */
public class HashTest {

    @Test
    public void addHashInfo(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数artifactId，artifactId不能重复使用，数据也不能太长
        String artifactId = "10c1d75390c718173bbb4086fab31c2a8ee87a99e2fe6cb99b843c57431ce21b";
        // 参数json
        String json = JSONObject.fromObject(getContent()).toString();
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.addHash(artifactId, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("addHash返回的数据："+resultData);
    }

    @Test
    public void hashTrackInfo(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数artifactId
        String artifactId = "6da471b33bc3b4480204a40337bccbfbd0ba14432703f0d829f742fe104a64ff";
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.hashTrack(artifactId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("hashTrack返回的数据："+resultData);
    }

    @Test
    public void addBatchHashInfo(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数json
        String json = JSONObject.fromObject(getContentBatch()).toString();
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.addBatchHash(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 服务器正常返回数据为空
        System.out.println("addBatchHash返回的数据："+resultData);
    }

    private static String getContent() {
        return  new GetContentUtil().getFileStringByBufferReader("C:\\Users\\admin\\Desktop\\cochainapi\\hash.json");
    }

    private static String getContentBatch() {
        return  new GetContentUtil().getFileStringByBufferReader("C:\\Users\\admin\\Desktop\\cochainapi\\hashBatch.json");
    }
}
