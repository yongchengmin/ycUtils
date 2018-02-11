package com.yc.utils.esbUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
public class requestUtil {

	public static final String ENCODING = "UTF-8";
	
	private final static String CONTENT_TYPE_APPLICATION_JSON = "application/json";


	public static String post(Map<String, String> paramMap, String url) throws Exception {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : paramMap.keySet()) {
				nvps.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)
					.setConnectionRequestTimeout(10000).setSocketTimeout(30000).build();
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = null;
			try {
				if (response != null) {
					entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(entity, ENCODING);
					}
				}
//				System.out.println(response.getStatusLine());
				// do something useful with the response body
				// and ensure it is fully consumed

				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return result;
	}

	public static String get(Map<String, String> paramMap, String url) throws Exception {
		String str = "";
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : paramMap.keySet()) {
				nvps.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
			str = EntityUtils.toString(new UrlEncodedFormEntity(nvps, ENCODING));
			HttpGet httpGet = new HttpGet(url + "?" + str);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = null;
			try {
				if (response != null) {
					entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(entity, ENCODING);
					}
				}
//				System.out.println(response.getStatusLine());
				// do something useful with the response body
				// and ensure it is fully consumed

				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return result;
	}
	
	
	public static String postRequest(String url, Map<String, Object> param) throws Exception {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)
					.setConnectionRequestTimeout(10000).setSocketTimeout(30000).build();
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

			Gson gson = new Gson();
			String parameter = gson.toJson(param);

			StringEntity se = new StringEntity(parameter,ENCODING);
			se.setContentType(CONTENT_TYPE_APPLICATION_JSON);
			httpPost.setEntity(se);

			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = null;
			try {
				if (response != null) {
					entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(entity, ENCODING);
					}
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return result;
	}

	
	public static String getResponseData(String url, String postData)throws Exception{
		String data = null;
		DataOutputStream wr = null;
		DataInputStream rd = null;
		HttpsURLConnection httpurlconnection = null;
		try {
			URL dataUrl = new URL(url);
			httpurlconnection = (HttpsURLConnection) dataUrl.openConnection();
			httpurlconnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
			httpurlconnection.setConnectTimeout(100000);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setRequestProperty("Proxy-Connection", "Keep-Alive");
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setDoInput(true);
			OutputStream os = httpurlconnection.getOutputStream();
			wr = new DataOutputStream(os);
			wr.write(postData.getBytes());
			wr.flush();
			wr.close();
			InputStream is = httpurlconnection.getInputStream();
			rd = new DataInputStream(is);
			byte d[] = new byte[rd.available()];
			rd.read(d);
			data = new String(d);
			httpurlconnection.disconnect();
		}  catch(ConnectException e) {
        	return "{\"code\":404,\"message\":\"Read timed out！\"}";
        } finally {
            if (rd != null) {
                rd.close();
                rd = null;
            }
            if (wr != null) {
                wr.close();
                wr = null;
            }
            if (httpurlconnection != null) {
            	httpurlconnection.disconnect();
            	httpurlconnection = null;
            }
        }
		return data;
	}
	
	public static String getResponsePost(String url, String postData)throws Exception{
		String data = null;
		DataOutputStream wr = null;
		DataInputStream rd = null;
		java.net.HttpURLConnection httpurlconnection = null;
		try {
			URL dataUrl = new URL(url);
			httpurlconnection = (HttpURLConnection) dataUrl.openConnection();
			httpurlconnection.setConnectTimeout(100000);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setRequestProperty("Proxy-Connection", "Keep-Alive");
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setDoInput(true);
			OutputStream os = httpurlconnection.getOutputStream();
			wr = new DataOutputStream(os);
			wr.write(postData.getBytes());
			wr.flush();
			wr.close();
			InputStream is = httpurlconnection.getInputStream();
			rd = new DataInputStream(is);
			byte d[] = new byte[rd.available()];
			rd.read(d);
			data = new String(d);
			httpurlconnection.disconnect();
		}  catch(ConnectException e) {
        	return "{\"code\":404,\"message\":\"Read timed out！\"}";
        } finally {
            if (rd != null) {
                rd.close();
                rd = null;
            }
            if (wr != null) {
                wr.close();
                wr = null;
            }
            if (httpurlconnection != null) {
            	httpurlconnection.disconnect();
            	httpurlconnection = null;
            }
        }
		return data;
	}
}
