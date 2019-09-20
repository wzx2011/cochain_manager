package com.manage.httpclient.cochain;

import com.manage.framework.AppConstants;
import com.manage.util.MD5Utils;
import net.sf.json.JSONObject;
import okhttp3.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUpchainUtil {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	static public String vehost = AppConstants.getValue("vehost");
	static public String token = AppConstants.getValue("token");
	static public String artifactid = AppConstants.getValue("artifactid");
	static public String artifacts = AppConstants.getValue("artifacts");
	static public String users = AppConstants.getValue("user");

	OkHttpClient client = new OkHttpClient().newBuilder()
			.connectTimeout(120, TimeUnit.SECONDS) // 设置连接超时
			.readTimeout(120, TimeUnit.SECONDS) // 设置读超时
			.writeTimeout(120, TimeUnit.SECONDS) // 设置写超时
			.build();

	public String token() throws Exception {
		Map<String, String> content = new HashMap<String, String>();
		String appid = AppConstants.getValue("appid");
		String appkey = AppConstants.getValue("appkey");
		Integer nonce = 111111;
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

		StringBuffer strBuf = new StringBuffer();

		strBuf = strBuf.append("appid=").append(appid)
					.append("&appkey=").append(appkey)
					.append("&nonce=").append(nonce)
					.append("&timestamp=").append(timestamp);
		String signature = MD5Utils.encryptSHA(strBuf.toString());

		content.put("appid", AppConstants.getValue("appid"));
		content.put("nonce", nonce.toString());
		content.put("timestamp", timestamp);
		content.put("signature", signature);

		RequestBody body = RequestBody.create(JSON, JSONObject.fromObject(content).toString());
		Request request = new Request.Builder().url(vehost + token)
				.addHeader("Content-Type", "application/json; charset=utf-8").post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return JSONObject.fromObject(response.body().string()).getString("token");
		}
	}

	public String artifacts(String url, String json) throws Exception {

		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json; charset=utf-8")
				.addHeader("x-api-token", this.token()).addHeader("x-user-uid", AppConstants.getValue("uid"))
				.addHeader("x-user-secretkey", AppConstants.getValue("secretkey")).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public String createUser(String url, String json) throws Exception {

		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("x-api-token", this.token())

				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public String sendAndGet(String url, String json) throws Exception {

		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("x-api-token", this.token())
				.addHeader("x-user-uid", AppConstants.getValue("uid"))
				.addHeader("x-user-secretkey", AppConstants.getValue("secretkey")).put(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public String get(String url, String json) throws Exception {

		Request request = new Request.Builder().url(url).get().build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public String artifactIds(String url, String json) throws Exception {

		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("x-api-token", this.token()).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	String bowlingJson() {
		Map<String, String> content = new HashMap<String, String>();
		content.put("batchNumber", "5");
		return JSONObject.fromObject(content).toString();
	}

	public static void main(String[] args) throws Exception {
		HttpUpchainUtil example = new HttpUpchainUtil();
		String json = example.bowlingJson();
		String response = example.token();
		System.out.println(response);

		String artifactIds = example.artifactIds(vehost + artifactid, json);
		System.out.println(artifactIds);
	}

	public String vechainResultLog(String url) throws Exception {
		HttpUpchainUtil example = new HttpUpchainUtil();
		return example.get(url, null);
	}

}
