package com.pj.service.mq.model;

import java.io.Serializable;

/**
 * 接口中心给到DB的实体
 * 消息发送体不能超过256kb
 * @author Fangpc
 *
 */
public class SmsInfo implements Serializable{
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1253736211622727436L;

	//短信内容
	private String a;
	
	//手机号码
	private String b;
	
	//外部订单号
	private String c;

	//手机号码(群发)
	private String d;
	
	//定时发送时间(YYYY-MM-DD HH:MM:SS)
	private Long h;
	
	//用户id
	private String i;
	
	//用户拓展码号
	private String j;

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

	public Long getH() {
		return h;
	}

	public void setH(Long h) {
		this.h = h;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getJ() {
		return j;
	}

	public void setJ(String j) {
		this.j = j;
	}

	@Override
	public String toString() {
		return "SmsInfo [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", h=" + h + ", i=" + i + ", j=" + j + "]";
	}


}