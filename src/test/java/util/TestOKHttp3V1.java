package util;

import com.manage.util.MD5Utils;
import net.sf.json.JSONObject;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: cochain_manager
 * @description: 测试cochain-api项目中V1包下面的控制器接口
 * @author: wzx
 * @create: 2019-07-09 09:17
 */
public class TestOKHttp3V1 {

    static public String X_API_TOKEN = "x-api-token";
    public static String X_USER_UID = "x-user-uid";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    // 39开发服务器地址
    static public String hostDev = "http://39.104.135.20/cochainapi";
    // 47测试服务器地址
    static public String hostTest = "http://47.92.65.164/cochainapi";
    // 本地测试地址
    static public String localhost = "http://localhost:8011";


    // 获取token的路径
    static public String token = "/v1/user/token";
    // 创建用户
    static public String createUser = "/v1/users";
    // 批量生成artifact路径
    static public String addArtifacts = "/v1/artifacts";
    // 生成单个artifact
    static public String addArtifact = "/v1/artifact";
    // 获取artifactId集合
    static public String getArtifactIds = "/v1/artifact_ids";
    // 获取artifact信息
    static public String getArtifacts = "/v1/artifacts/artifact_id";
    // 获取artifactTrack信息
    static public String addTracks = "/v1/artifacts/artifact_id/track";
    // 添加Hash信息
    static public String addHash = "/v1/artifacts/artifact_id/hash";
    // 获取hash的追溯信息
    static public String hashTrack = "/v1/artifacts/artifact_id/hash";
    // 批量添加Hash信息
    static public String addBatchHash = "/v1/artifacts/batchHash";
    // 获取积分
    static public String getJifen = "/v1/jifen/username/track";
    // 获取积分历史
    static public String getJifenHistory = "/v1/jifen/username/tracks";
    // 获取二维码的区块链信息
    static public String goBlockChain = "/goBlockChain/artifact_id";

    // 请求头参数配置
    static public String uid = "0x15ddfb4a8211882ab704f6dd99fc084654564b7241da93886f734b961bd05ea2";
    static public String secretkey = "123456";
    static public String appid = "8201b6ac0c264063dae903446fc837d2";
    static public String appkey = "d5cf48ac3cf3942f5bf248a456bff1afbb749ddb29ae5d0f8e7c804b5df2829b";

    // 日志
    private static Logger logger = Logger.getLogger(TestOKHttp3V1.class);

    // 设置连接超时、读写超时时间
    OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();

    /**
     * 获取token
     * @return
     * @throws Exception
     */
    public String token() throws Exception {
        RequestBody body = tokenParamters();
        Request request = new Request.Builder()
                .url("https://ib.vechain.com/auth/v1/tokens")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\ntoken接口数据："+result);
            return JSONObject.fromObject(result).getString("token");
        }
    }

    /**
     * 整理token所需的参数
     * @return
     * @throws Exception
     */
    RequestBody tokenParamters() throws Exception {
        Map<String, String> content = new HashMap<String, String>();
        Integer nonce = 111111;
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

        StringBuffer strBuf = new StringBuffer();

        strBuf = strBuf.append("appid=").append(appid)
                .append("&appkey=")
                .append(appkey).append("&nonce=").append(nonce)
                .append("&timestamp=").append(timestamp);
        String signature = MD5Utils.encryptSHA(strBuf.toString());

        content.put("appid", appid);
        content.put("nonce", nonce.toString());
        content.put("timestamp", timestamp);
        content.put("signature", signature);

        return RequestBody.create(JSON, JSONObject.fromObject(content).toString());
    }

    /**
     * 批量生成artifact
     * @param json
     * @return
     * @throws Exception
     */
    public String artifacts(String json) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(localhost+addArtifacts)
                .addHeader("x-api-token",this.token())
                .addHeader("x-user-uid", uid)
                .addHeader("x-user-secretkey", secretkey)
                .addHeader("x-app-index", "1")
                .post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\nartifacts接口数据："+result);
            return result;
        }
    }

    /**
     * 生成单个artifactId
     * @param json
     * @return
     * @throws Exception
     */
    public String artifact(String json) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(localhost+addArtifact)
                .addHeader("x-api-token",this.token())
                .addHeader("x-user-uid", uid)
                .addHeader("x-user-secretkey", secretkey)
                .addHeader("x-app-index", "1")
                .post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\nartifacts接口数据："+result);
            return result;
        }
    }

    /**
     * 获取artifactId的集合
     * @param json
     * @return
     * @throws Exception
     */
    public String artifactIds(String json) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(localhost+getArtifactIds)
                .addHeader("x-api-token",this.token())
                .post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\nartifactIds接口数据："+result);
            return result;
        }
    }

    /**
     * 获取单个artifactId的信息
     * @param bottleId
     * @return
     * @throws Exception
     */
    public String getArtifacts(String bottleId) throws Exception {
        String url = localhost+getArtifacts.replace("artifact_id",bottleId);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\ngetArtifacts接口数据："+result);
            return result;
        }
    }

    /**
     * 获取artifactId的交易信息
     * @param artifactId
     * @return
     * @throws Exception
     */
    public String addTracks(String artifactId) throws Exception {
        String url = localhost+addTracks.replace("artifact_id",artifactId);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\naddTracks接口数据："+result);
            return result;
        }
    }

    /**
     * 获取artifactId的交易信息post请求
     * @param artifactId
     * @return
     * @throws Exception
     */
    public String addTracksPost(String artifactId, String json) throws Exception {
        String url = localhost+addTracks.replace("artifact_id",artifactId);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .addHeader("x-user-uid",uid)
                .post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\naddTracksPost接口数据："+result);
            return result;
        }
    }

    /**
     * 调用cochain-api项目接口新增t_user信息
     * @param role
     * @param secretkey
     * @return
     * @throws Exception
     */
    public String createUser(String token, String role, String secretkey) throws Exception {
        String json = createUserParamters(role,secretkey);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(localhost + createUser)
                .addHeader(X_API_TOKEN,token).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\ncreateUser接口数据："+result);
            return result;
        }
    }

    /**
     * 整理创建用户的参数
     * @param role
     * @param secretkey
     * @return
     */
    String createUserParamters(String role, String secretkey) {
        Map<String, String> content = new HashMap<String, String>();
        content.put("role", role);
        content.put("secretkey", secretkey);
        return JSONObject.fromObject(content).toString();
    }

    /**
     * 添加hash相关信息
     * @param artifactId
     * @param json
     * @return
     * @throws Exception
     */
    public String addHash(String artifactId, String json) throws Exception {
        String url = localhost+addHash.replace("artifact_id",artifactId);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .addHeader("x-user-uid", uid)
                .post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\naddHash接口数据："+result);
            return result;
        }
    }

    /**
     * 获取hash追溯信息
     * @param artifactId
     * @return
     * @throws Exception
     */
    public String hashTrack(String artifactId) throws Exception {
        String url = localhost+hashTrack.replace("artifact_id",artifactId);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\nhashTrack接口数据："+result);
            return result;
        }
    }

    /**
     * 批量添加hash信息
     * @param json
     * @return
     * @throws Exception
     */
    public String addBatchHash(String json) throws Exception {
        String url = localhost+addBatchHash;
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .post(body).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\naddBatchHash接口数据："+result);
            return result;
        }
    }

    /**
     * 获取积分信息
     * @param username
     * @return
     * @throws Exception
     */
    public String getJifen(String username) throws Exception {
        String url = localhost + getJifen.replace("username",username);
        Request request = new Request.Builder()
                .url(url)
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\ngetJifen接口数据："+result);
            return result;
        }
    }

    /**
     * 获取用户积分历史信息
     * @param username
     * @return
     * @throws Exception
     */
    public String getJifenHistory(String username) throws Exception {
        String url = localhost + getJifenHistory.replace("username",username);
        Request request = new Request.Builder()
                .url(url)
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\ngetJifenHistory接口数据："+result);
            return result;
        }
    }

    /**
     * 调用接口，不返回信息
     * @param artifactId
     * @throws Exception
     */
    public void goBlockChain(String artifactId) throws Exception {
        String url = localhost + goBlockChain.replace("artifact_id",artifactId);
        Request request = new Request.Builder()
                .url(url)
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            System.out.println("goBlockChain返回数据："+result);
        }
    }

    public String getTxhash(String artifact_id) throws Exception {
        String url = "https://ib.vechain.com/artifact-api/v1/artifacts/txids/artifact_id";
        url = url.replace("artifact_id",artifact_id);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            logger.info("\ngetTxhash接口数据："+result);
            return result;
        }
    }

    /**
     * 根据artifact_id获取txHash
     * @param url
     * @return
     * @throws Exception
     */
    public JSONObject getTxhash1(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-token",this.token())
                .get().build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            return JSONObject.fromObject(result);
        }
    }

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
}
