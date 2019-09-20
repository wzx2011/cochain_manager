package v1;

import com.manage.util.GetContentUtil;
import net.sf.json.JSONObject;
import org.junit.Test;
import util.TestOKHttp3V1;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cochain_manager
 * @description: 测试cochain-api项目下面的v1包中的第三方接口
 * @author: wzx
 * @create: 2019-07-09 11:30
 */
public class ArtifactsTest {

    /**
     * dig红酒信息追溯系统--基础信息上链接口
     */
    @Test
    public void addArtifacts(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数json
        String json = JSONObject.fromObject(getContent()).toString();
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.artifacts(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("批量生成artifacts："+resultData);
    }

    @Test
    public void addArtifact(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数json
        String json = JSONObject.fromObject(getContent()).toString();
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.artifact(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("生成单个artifacts："+resultData);
    }

    /**
     * dig红酒信息追溯系统--下载二维码时获取二维码后缀的artifactId的接口
     */
    @Test
    public void getArtifactIds(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数json
        Map<String, Object> map = new HashMap<>();
        map.put("batchNumber", "3");
        String json = JSONObject.fromObject(map).toString();
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.artifactIds(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("返回的数据："+resultData);
    }

    @Test
    public void getArtifacts(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数bottleId
        String bottleId = "d4d5bc271738e9171a572da017a3558853e55484ea0544640d7fb3e669f3a232";
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.getArtifacts(bottleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("返回的数据："+resultData);
    }

    /**
     * 生成artifact的数据
     * @return
     */
    private static String getContent() {
        return  new GetContentUtil().getFileStringByBufferReader("C:\\Users\\admin\\Desktop\\cochainapi\\artifacts1.json");
    }
}
