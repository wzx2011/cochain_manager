package com.manage.httpclient.cochain;

import com.manage.framework.AppConstants;
import com.manage.util.MD5Utils;
import net.sf.json.JSONObject;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OKHttp3Util {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	static public String host = "http://39.104.135.20/cochainapi";
	static public String createUserUrl = "/v1/users";
	static public String X_API_TOKEN = "x-api-token";
	static public String token = "/v1/user/token";

	static public String artifactid = "/v1/artifact_ids";
	static public String artifacts = "/v1/artifacts";
	static public String track = "/v1/artifacts/";
	Logger logger = Logger.getLogger(OKHttp3Util.class);
	OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(120, TimeUnit.SECONDS)
			.readTimeout(120, TimeUnit.SECONDS)
			.writeTimeout(120, TimeUnit.SECONDS)
			.build();

	/**
	 * 调用cochainapi项目接口获取token信息
	 * @param appid
	 * @param appkey
	 * @return
	 * @throws Exception
	 */
	public String getToken(String appid, String appkey) throws Exception {
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

		RequestBody body = RequestBody.create(JSON, JSONObject.fromObject(content).toString());
		Request request = new Request.Builder()
				.url(host+token)
				.addHeader("Content-Type", "application/json; charset=utf-8")
				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return JSONObject.fromObject(result).getString("token");
		}
	}

	/**
	 * 调用cochainapi项目接口新增t_user信息
	 * @param token
	 * @param role
	 * @param secretkey
	 * @return
	 * @throws Exception
	 */
	public JSONObject createUserApi(String token, String role, String secretkey) throws Exception {
		String json = userApiParamters(role,secretkey);
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(host + createUserUrl)
				.addHeader(X_API_TOKEN,token).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return JSONObject.fromObject(result);
		}
	}

	String userApiParamters(String role, String secretkey) {
		Map<String, String> content = new HashMap<String, String>();
		content.put("role", role);
		content.put("secretkey", secretkey);
		return JSONObject.fromObject(content).toString();
	}

	/**
	 * 基础信息上链
	 * @param url
	 * @param jsonData
	 * @param token
	 * @param uid
	 * @param secretkey
	 * @return
	 * @throws Exception
	 */
	public JSONObject baseInfoToChain(String url, String jsonData, String token, String uid, String secretkey) throws Exception {
		String json = JSONObject.fromObject(jsonData).toString();
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.addHeader("x-api-token",token)
				.addHeader("x-user-uid", uid)
				.addHeader("x-user-secretkey", secretkey)
				.addHeader("x-app-index", "1")
				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return JSONObject.fromObject(result);
		}
	}

	/**
	 * 获取artifactIds数组
	 * @param json
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public JSONObject getArtifactIds(String json, String token) throws Exception {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(host+artifactid)
				.addHeader("x-api-token", token)
				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return JSONObject.fromObject(result);
		}
	}

	/**
	 * 交易信息上链
	 * @param url
	 * @param jsonData
	 * @param token
	 * @param uid
	 * @param secretkey
	 * @return
	 * @throws Exception
	 */
	public JSONObject traceInfoToChain(String url, String jsonData, String token, String uid, String secretkey) throws Exception {
		String json = JSONObject.fromObject(jsonData).toString();
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.addHeader("x-api-token",token)
				.addHeader("x-user-uid", uid)
				.addHeader("x-user-secretkey", secretkey)
				.addHeader("x-app-index", "1")
				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return JSONObject.fromObject(result);
		}
	}

	public static void main(String[] args) throws Exception {
		OKHttp3Util a = new OKHttp3Util();
		System.out.println(a.getToken("3c6371885785cbf25a26bafa82df001f","b31822cc884939571d976f9fd20af256e5faec6b1e891fdcc8c2659634b0d472"));
	}

}
