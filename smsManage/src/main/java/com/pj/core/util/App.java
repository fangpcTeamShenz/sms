package com.pj.core.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.pj.core.util.GsonUtils;
import com.pj.core.util.HttpUtils;
import com.pj.core.util.MD5Util;

/**
 * 
 */
public class App {
	public static void main(String[] args) throws Exception {
		JsonRootBean rootBean = new JsonRootBean();
		rootBean.setAccessKey("786957b81c3db271a0b7b88ae2d5c982");
		rootBean.setPhoneNumber("13410061172");//
		rootBean.setContent("【景沛】你好世界");
		rootBean.setOutOrderId(String.valueOf(new Date().getTime()));
		rootBean.setTimestamp(String.valueOf(new Date().getTime()));
		rootBean.setSign(MD5Util.MD5Encode(rootBean.getOutOrderId()+"bf688cefd04199b5dff3abe33ceafb28"+rootBean.getTimestamp(), "utf-8"));
		
		HttpUtils httpUtils = new HttpUtils();
//		System.out.println("ret:"+httpUtils.postStringEntity("http://127.0.0.1:8089/receive", new GsonUtils().toJson(rootBean), "utf-8"));
//		System.out.println("ret:"+httpUtils.postStringEntity("http://101.132.78.201:8089/receive", new GsonUtils().toJson(rootBean), "utf-8"));
		String result = httpUtils.postStringEntity("http://101.132.78.201:8089/receive", new GsonUtils().toJson(rootBean), "utf-8");
		Map<String,Object> map = GsonUtils.fromJson(result, Map.class);
		System.out.println(map.get("statusCode"));
		
	}
	
	public static class JsonRootBean implements Serializable{
		private static final long serialVersionUID = -7226710820991773347L;
		//系统参数
	    private String accessKey;//注册获取的key
	    private String sign;//签名MD5(jsonRootBean.getOutOrderId()+appSecret+jsonRootBean.getTimestamp())
	    private String timestamp;//时间戳
	    //业务参数
	    private String phoneNumber;//单个手机号码
		private String phoneNumbers;//群发手机号码
	    private String content;//发送内容
	    private String outOrderId;//外部订单号
	    private Long startdelivertime;//定时发送时间
		private String extNumber;//拓展码号
		public String getAccessKey() {
			return accessKey;
		}
		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getPhoneNumbers() {
			return phoneNumbers;
		}
		public void setPhoneNumbers(String phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getOutOrderId() {
			return outOrderId;
		}
		public void setOutOrderId(String outOrderId) {
			this.outOrderId = outOrderId;
		}
		public Long getStartdelivertime() {
			return startdelivertime;
		}
		public void setStartdelivertime(Long startdelivertime) {
			this.startdelivertime = startdelivertime;
		}
		public String getExtNumber() {
			return extNumber;
		}
		public void setExtNumber(String extNumber) {
			this.extNumber = extNumber;
		}
		
		
		
	}
	
}
