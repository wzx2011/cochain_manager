package com.manage.httpclient.cochain;

import com.manage.util.TimeUtil;
import net.sf.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GetTX {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	static public String getBlockchainTx = "http://47.92.65.164/cochainapi/query/getBlockchain/wine/";
	 

	OkHttpClient client = new OkHttpClient().newBuilder()
			.connectTimeout(120, TimeUnit.SECONDS) // 设置连接超时
			.readTimeout(120, TimeUnit.SECONDS) // 设置读超时
			.writeTimeout(120, TimeUnit.SECONDS) // 设置写超时
			.build();

	public JSONObject getBlockchainDataByTx(String txid) throws Exception {

		Request request = new Request.Builder().url(getBlockchainTx+txid)
				.addHeader("Content-Type", "application/json; charset=utf-8").get().build();
		try (Response response = client.newCall(request).execute()) {
			return JSONObject.fromObject(response.body().string());
		}
	}
	
	public static void main(String args[]) throws Exception {
		String date = new GetTX().getBlockchainDataByTx("15d6baf0a3905edff5a85b98ffed6fa4ee927a4ad52c61d0239dc98140392740").getJSONObject("trans").getString("time");
		System.out.println(date);
		Date dat = new Date(TimeUtil.getTimestamp(date));
		System.out.println(dat);
		System.out.println(TimeUtil.getnyr(dat));
		System.out.println(TimeUtil.getGlobalTime(dat));
	}
}
