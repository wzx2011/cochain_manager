package com.manage.httpclient.cochain;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.manage.util.MD5Utils;
import net.sf.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestOkhttp {
	public static final MediaType JSON = MediaType.parse("application/json; charset=UTF-8");
	static public String host = "http://39.104.135.20/cochainapi"; 
	static public String token = "/v1/user/token"; 
	static public String artifacts = "/v1/artifacts";
	static public String track = "/v1/artifacts/";
	static public String uid = "46FCD53D5D39632B21A093C8A0595D89";
	static public String secretkey = "41FBBB532B5F34707205A31D60C4BD16";
	static public String appid = "3c6371885785cbf25a26bafa82df001f";
	static public String appkey = "b31822cc884939571d976f9fd20af256e5faec6b1e891fdcc8c2659634b0d472";
	OkHttpClient client = new OkHttpClient().newBuilder()
			.connectTimeout(120, TimeUnit.SECONDS)      //设置连接超时
	        .readTimeout(120, TimeUnit.SECONDS)         //设置读超时
	        .writeTimeout(120, TimeUnit.SECONDS)        //设置写超时
			.build();

	public String token() throws Exception {
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

    public String artifacts(String url, String json) throws Exception {

		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.addHeader("x-api-token",this.token())
				.addHeader("x-user-uid", uid)
				.addHeader("x-user-secretkey", secretkey)
				.addHeader("x-app-index", "1")
				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return result;
		}
	}
    
    public String createUser(String url, String json) throws Exception {

 		RequestBody body = RequestBody.create(JSON, json);
 		Request request = new Request.Builder()
 				.url(url)
 				.addHeader("x-api-token",this.token())
 				 
 				.post(body).build();
 		try (Response response = client.newCall(request).execute()) {
 			String result = response.body().string();
			return result;
 		}
 	}
	
	public String sendAndGet(String url, String json) throws Exception {
		 
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.addHeader("x-api-token",this.token())
				.addHeader("x-user-uid", uid)
				.addHeader("x-user-secretkey", secretkey)
				.addHeader("x-app-index", "1")
				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return result;
		}
	}
	
	public String get(String url, String json) throws Exception {
		 
		Request request = new Request.Builder()
				.url(url)
				.get().build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return result;
		}
	}
	
	public String artifactIds(String url, String json) throws Exception {
		 
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.addHeader("x-api-token",this.token())
				.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String result = response.body().string();
			return result;
		}
	}

	String bowlingJson() {
		Map<String, String> content = new HashMap<String, String>();
		content.put("batchNumber", "5");
		return JSONObject.fromObject(content).toString();
	}
	
	String userJson() {
		Map<String, String> content = new HashMap<String, String>();
		content.put("secretkey", "001");
		content.put("role", "2");
		return JSONObject.fromObject(content).toString();
	}
	
	String artifactJson() {
		Map<String, String> content = new HashMap<String, String>();
		content.put("secretkey", "gujiao6");
		content.put("role", "3");
		return JSONObject.fromObject(content).toString();
		
		
	}

	public static void main(String[] args) throws Exception {
		TestOkhttp to = new TestOkhttp();
//		to.artifacts(host+artifacts, new GetContentUtil().getFileStringByBufferReader("C:\\Users\\lenovo\\Desktop\\cigarette.json"));
//		 to.token();
		 System.out.println(to.token());
//		to.sendAndGet(host+track+"6a7b3d29794ff74bf58cf221fd154f89693e758dfeffd989d5ed116d766f453e"+"/track", 
//				new GetContentUtil().getFileStringByBufferReader("C:\\Users\\lenovo\\Desktop\\Desktop\\sendAndGet.json"));
//		char buffer[] =  {123, 34, 97, 114, 116, 105, 102, 97, 99, 116, 115, 34, 58, 32, 91, 123, 34, 99, 104, 105, 108, 100, 114, 101, 110, 34, 58, 32, 91, 123, 34, 99, 104, 105, 108, 100, 114, 101, 110, 34, 58, 32, 91, 123, 34, 105, 100, 34, 58, 32, 34, 54, 97, 55, 98, 51, 100, 50, 57, 55, 57, 52, 102, 102, 55, 52, 98, 102, 53, 56, 99, 102, 50, 50, 49, 102, 100, 49, 53, 52, 102, 56, 57, 54, 57, 51, 101, 55, 53, 56, 100, 102, 101, 102, 102, 100, 57, 56, 57, 100, 53, 101, 100, 49, 49, 54, 100, 55, 54, 54, 102, 52, 53, 97, 101, 34, 44, 34, 99, 111, 100, 101, 34, 58, 34, 49, 113, 97, 122, 50, 119, 115, 120, 34, 44, 34, 113, 117, 101, 114, 121, 78, 117, 109, 34, 58, 48, 125, 93, 44, 34, 105, 100, 34, 58, 32, 34, 98, 97, 97, 55, 57, 53, 100, 98, 49, 57, 57, 50, 52, 98, 56, 102, 53, 101, 98, 54, 48, 56, 55, 49, 51, 55, 101, 54, 57, 97, 102, 100, 54, 55, 53, 102, 100, 49, 57, 102, 49, 97, 102, 50, 99, 49, 97, 57, 57, 53, 56, 53, 53, 54, 50, 53, 49, 49, 48, 49, 53, 100, 48, 48, 34, 44, 34, 112, 114, 111, 112, 101, 114, 116, 105, 101, 115, 34, 58, 32, 123, 34, 105, 109, 97, 103, 101, 34, 58, 32, 91, 34, 104, 116, 116, 112, 58, 47, 47, 99, 111, 115, 99, 102, 46, 99, 111, 99, 104, 97, 105, 110, 46, 99, 110, 47, 105, 109, 97, 103, 101, 115, 47, 49, 46, 106, 112, 103, 34, 44, 34, 104, 116, 116, 112, 58, 47, 47, 99, 111, 115, 99, 102, 46, 99, 111, 99, 104, 97, 105, 110, 46, 99, 110, 47, 105, 109, 97, 103, 101, 115, 47, 50, 46, 106, 112, 103, 34, 44, 34, 104, 116, 116, 112, 58, 47, 47, 99, 111, 115, 99, 102, 46, 99, 111, 99, 104, 97, 105, 110, 46, 99, 110, 47, 105, 109, 97, 103, 101, 115, 47, 51, 46, 106, 112, 103, 34, 93, 44, 34, 110, 97, 109, 101, 34, 58, 32, 34, -24, -117, -113, -25, -125, -97, 34, 44, 34, 117, 114, 108, 34, 58, 32, 34, 104, 116, 116, 112, 58, 47, 47, 97, 112, 105, 100, 101, 118, 46, 99, 111, 99, 104, 97, 105, 110, 46, 99, 110, 47, 68, 73, 71, 87, 73, 84, 80, 47, 99, 105, 103, 114, 97, 116, 116, 101, 47, 34, 44, 34, 99, 111, 110, 116, 101, 110, 116, 34, 58, 32, 123, 32, 34, 110, 105, 99, 111, 116, 105, 110, 101, 34, 32, 58, 34, 48, 46, 57, 109, 103, 34, 44, 34, 116, 97, 114, 34, 58, 34, 49, 50, 109, 103, 34, 44, 34, 116, 121, 112, 101, 34, 58, 34, -25, -125, -92, -25, -125, -97, -27, -98, 63, 34, 44, 34, 102, 97, 99, 116, 111, 114, 121, 34, 58, 34, -27, -66, -112, -27, -73, -98, -27, -115, -73, -25, -125, -97, -27, -114, 63, 34, 44, 34, 112, 108, 97, 116, 102, 111, 114, 109, 34, 58, 34, 49, 48, 50, 53, 54, 53, 34, 44, 34, 73, 100, 101, 110, 116, 105, 102, 105, 99, 97, 116, 105, 111, 110, 95, 99, 104, 101, 99, 107, 34, 58, 34, 65, 71, 53, 52, 51, 50, 52, 49, 34, 44, 34, 80, 97, 99, 107, 105, 110, 103, 95, 115, 101, 97, 108, 34, 58, 34, 69, 53, 54, 56, 68, 68, 83, 65, 34, 32, 44, 34, 84, 99, 111, 100, 101, 34, 58, 34, 84, 45, 49, 53, 52, 50, 53, 34, 44, 34, 88, 99, 111, 100, 101, 34, 58, 34, 88, 45, 50, 53, 53, 54, 34, 44, 34, 115, 97, 108, 101, 32, 97, 114, 101, 97, 34, 58, 34, -26, -79, -97, -24, -117, -113, 32, -27, -66, -112, -27, -73, -98, 34, 44, 34, 109, 101, 114, 99, 104, 97, 110, 116, 34, 58, 34, -28, -72, -118, -26, -75, -73, -27, -104, -119, -25, -90, -66, -25, -125, -97, -24, -95, -116, 34, 44, 34, 100, 97, 116, 101, 32, 111, 102, 32, 109, 97, 110, 117, 102, 97, 99, 116, 117, 114, 101, 34, 58, 34, 50, 48, 49, 55, 45, 48, 57, 45, 48, 49, 32, 49, 50, 58, 48, 49, 58, 51, 50, 34, 44, 34, 100, 101, 109, 111, 95, 112, 97, 103, 101, 34, 58, 34, 99, 105, 103, 114, 97, 116, 116, 101, 34, 44, 125, 125, 125, 93, 44, 34, 105, 100, 34, 58, 32, 34, 100, 50, 49, 100, 100, 101, 51, 52, 52, 49, 55, 98, 102, 50, 52, 53, 50, 97, 56, 100, 98, 57, 102, 56, 51, 98, 55, 101, 49, 100, 51, 50, 57, 99, 55, 99, 49, 98, 100, 52, 51, 56, 50, 99, 48, 99, 98, 51, 97, 100, 99, 52, 101, 52, 101, 100, 50, 50, 53, 57, 57, 102, 54, 54, 34, 125, 93, 125};
		
	}
}
