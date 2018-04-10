package com.pj.service.mq.model;

import java.io.Serializable;
import java.util.List;

/**
 * 上行
 * @author Fangpc
 *
 */
public class SmsUpstream implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4731523894990038985L;
	//端口id
//	private String moduleId;
	//用户回复消息
	private String a;
	//接受时间（yyyy-MM-dd HH:mm:ss）
	private String b;
	//发送手机号码
	private String c;
	//码号(用户短信回复的资源号码)
	private String d;
	//批量上行,优先处理
	private List<SmsUpstream> e;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public List<SmsUpstream> getE() {
		return e;
	}
	public void setE(List<SmsUpstream> e) {
		this.e = e;
	}
	
}
