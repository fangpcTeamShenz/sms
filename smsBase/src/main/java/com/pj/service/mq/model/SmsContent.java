package com.pj.service.mq.model;

import java.io.Serializable;

/**
 * 业务中心发送给执行模块的实体
 * 消息发送体不能超过256kb
 * @author Xiaotao
 *
 */
public class SmsContent implements Serializable{
    
	private static final long serialVersionUID = 1L;

	//短信内容
	private String a;
	
	//手机号码
	private String b;
	
	//群发信息体'记录主键_号码|记录主键_号码|'
	private String c;
	
	//用户短信记录主键
	private Long d;
	
	//码号
	private String e;
	
	//对接模块id
	private Long f;
	
	//商户id
	private Long g;
	
	//签名(md5(key+g+f))
	private String h;

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

	public Long getD() {
		return d;
	}

	public void setD(Long d) {
		this.d = d;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public Long getF() {
		return f;
	}

	public void setF(Long f) {
		this.f = f;
	}

	public Long getG() {
		return g;
	}

	public void setG(Long g) {
		this.g = g;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

}