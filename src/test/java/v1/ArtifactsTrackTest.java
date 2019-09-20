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
public class ArtifactsTrackTest {

    @Test
    public void getArtifacts(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数artifactId
        String artifactId = "6da471b33bc3b4480204a40337bccbfbd0ba14432703f0d829f742fe104a64ff";
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.addTracks(artifactId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("返回的数据："+resultData);
        // 服务器可以查询到数据，最后返回数据时出错，现在先不管的
    }

    @Test
    public void getArtifactsPost(){
        TestOKHttp3V1 ok = new TestOKHttp3V1();
        // 参数artifactId
        String artifactId = "6da471b33bc3b4480204a40337bccbfbd0ba14432703f0d829f742fe104a64ff";
        // 参数json
        String json = JSONObject.fromObject(getContent()).toString();
        // 返回数据
        String resultData = null;
        try {
            resultData = ok.addTracksPost(artifactId,json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("post请求返回的数据："+resultData);
    }

    private static String getContent() {
        return  new GetContentUtil().getFileStringByBufferReader("C:\\Users\\admin\\Desktop\\cochainapi\\hash.json");
    }

}
