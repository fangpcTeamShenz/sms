package com.pj.core.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
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
	
	@SuppressWarnings("resource")
	public String postStringEntity(String url, String json,String charset) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        StringEntity se = new StringEntity(json ,"UTF-8");
//        se.setContentType("text/json");
//        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);
        HttpResponse resp = httpClient.execute(httpPost);
        return EntityUtils.toString(resp.getEntity(), charset);
    }
}
