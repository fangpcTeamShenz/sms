package com.pj.core.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

@SuppressWarnings({ "unused", "deprecation" })
public class HttpUtils {
	public String post(String url, Map<String, String> params) throws Exception {
		return post(url, params, 10, "UTF-8");
	}

	public String post(String url, Map<String, String> params, String charset)
			throws Exception {
		return post(url, params, 10, charset);
	}

	public String post(String url, Map<String, String> params, int timeout)
			throws Exception {
		return post(url, params, timeout, "UTF-8");
	}
	public String get(String url, Map<String, String> params, String charset)
			throws Exception {
		return get(url, params, 10, charset);
	}

	public String get(String url, Map<String, String> params) throws Exception {
		return get(url, params, 10, "UTF-8");
	}

	public String get(String url, Map<String, String> params, int timeout)
			throws Exception {
		return get(url, params, timeout, "UTF-8");
	}

	@SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public String post(String url, Map<String, String> params, int timeout,
			String charset) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout",
				timeout * 1000);
		httpclient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		String retVal = "";
		try {
			List formparams = new ArrayList();
			if (params != null) {
				for (Map.Entry param : params.entrySet()) {
					formparams.add(new BasicNameValuePair((String) param
							.getKey(), (String) param.getValue()));
				}
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					charset);
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(entity);
			HttpResponse resp = httpclient.execute(httppost);
			retVal = EntityUtils.toString(resp.getEntity(), charset);
		} catch (IOException e) {
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return retVal;
	}

//	public static String post(String url, Map<String, String> params,
//			int timeout, File file, String filePro) throws IOException {
//		HttpClient httpclient = new DefaultHttpClient();
//		httpclient.getParams().setIntParameter("http.socket.timeout",
//				timeout * 1000);
//		httpclient.getParams().setBooleanParameter(
//				"http.protocol.expect-continue", false);
//		String retVal = "";
//		try {
//			MultipartEntity reqEntity = new MultipartEntity();
//			HttpPost httppost = new HttpPost(url);
//			httppost.setEntity(reqEntity);
//			for (Map.Entry param : params.entrySet()) {
//				reqEntity.addPart((String) param.getKey(), new StringBody(
//						(String) param.getValue()));
//			}
//			if ((file != null) && (file.exists())) {
//				FileBody bin = new FileBody(file);
//				reqEntity.addPart(filePro, bin);
//			}
//			ResponseHandler responseHandler = new BasicResponseHandler();
//			retVal = new String(((String) httpclient.execute(httppost,
//					responseHandler)).getBytes("ISO-8859-1"), "UTF-8");
//		} catch (IOException e) {
//			throw e;
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		return retVal;
//	}

	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	public String get(String url, Map<String, String> params, int timeout,
			String charset) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout",
				timeout * 1000);
		httpclient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		String retVal = "";
		try {
			List qparams = new ArrayList();
			if (params != null) {
				for (Map.Entry param : params.entrySet()) {
					qparams.add(new BasicNameValuePair((String) param.getKey(),
							(String) param.getValue()));
				}
			}
			String paramstr = URLEncodedUtils.format(qparams, charset);
			if (StringUtils.isNotEmpty(paramstr)) {
				url = url + "?" + paramstr;
			}
			HttpGet httpget = new HttpGet(url);

			HttpResponse resp = httpclient.execute(httpget);
			retVal = EntityUtils.toString(resp.getEntity(), charset);
		} catch (IOException e) {
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return retVal;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public static String get(String url, Map<String, String> params,
			int timeout, String charset, String fNameEndChar) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout",
				timeout * 1000);
		httpclient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		String retVal = "";
		fNameEndChar = (fNameEndChar == null) || ("".endsWith(fNameEndChar)) ? "?"
				: fNameEndChar;
		try {
			List qparams = new ArrayList();
			if (params != null) {
				for (Map.Entry param : params.entrySet()) {
					qparams.add(new BasicNameValuePair((String) param.getKey(),
							(String) param.getValue()));
				}
			}
			String paramstr = URLEncodedUtils.format(qparams, charset);
			if (StringUtils.isNotEmpty(paramstr)) {
				url = url + fNameEndChar + paramstr;
			}
			HttpGet httpget = new HttpGet(url);

			HttpResponse resp = httpclient.execute(httpget);
			retVal = EntityUtils.toString(resp.getEntity(), charset);
		} catch (IOException e) {
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return retVal;
	}
	
	
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
	

	@SuppressWarnings("resource")
	public String postStringEntity(String url, String json,String charset) throws Exception {
		BasicHttpParams httpParams = new BasicHttpParams();  
    	HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);  
    	HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000); 
    	
    	DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		httpClient.getParams().setIntParameter("http.socket.timeout", 5 * 1000);//100S超时
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5 * 1000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5 * 1000);
		
        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        StringEntity se = new StringEntity(json ,"UTF-8");
        se.setContentEncoding("UTF-8");
//        se.setContentType("text/json");
//        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);
        HttpResponse resp = httpClient.execute(httpPost);
        return EntityUtils.toString(resp.getEntity(), charset);
    }
	
	@SuppressWarnings("resource")
	public String postStringEntity(String url, String json,String charset, Integer timeout) throws Exception {
		BasicHttpParams httpParams = new BasicHttpParams();  
    	HttpConnectionParams.setConnectionTimeout(httpParams, timeout);  
    	HttpConnectionParams.setSoTimeout(httpParams, timeout); 
    	
    	DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		httpClient.getParams().setIntParameter("http.socket.timeout", timeout);//100S超时
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
	
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        StringEntity se = new StringEntity(json ,"UTF-8");
        se.setContentEncoding("UTF-8");
        httpPost.setEntity(se);
        HttpResponse resp = httpClient.execute(httpPost);
        return EntityUtils.toString(resp.getEntity(), charset);
    }
}
