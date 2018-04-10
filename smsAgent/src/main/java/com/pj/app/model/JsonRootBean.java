/**
  * Copyright 2017 bejson.com 
  */
package com.pj.app.model;
import java.io.Serializable;
/**
 * Auto-generated: 2017-03-14 14:41:1
 * 请求发送短信bean
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean implements Serializable{

    /**
	 * 
	 */
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