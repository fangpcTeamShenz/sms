package com.pj.service.mq.model;

import java.io.Serializable;
import java.util.List;

/**
 * 短信回执通知
 * @author Xtao
 *
 */
public class SmsReceipt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3291065652565027895L;
	//用户短信记录主键
	private Long a;
	//状态 1:成功,2:失败
	private int b;
	//短信被用户接收时间(yyyy-MM-dd HH:mm:ss)
	private String c;
	//网关附带消息
	private String d;
	//商户id
	private Long f;
	//批量短信回执,优先取
	private List<SmsReceipt> e;
	
	public Long getA() {
		return a;
	}
	public void setA(Long a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
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

	public List<SmsReceipt> getE() {
		return e;
	}

	public void setE(List<SmsReceipt> e) {
		this.e = e;
	}
	public Long getF() {
		return f;
	}
	public void setF(Long f) {
		this.f = f;
	}
	
}