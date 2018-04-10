package com.pj.service.redis.model;

import java.io.Serializable;

/**
 * 手机信息实体,待改造
 * @author Xtao
 *
 */
public class RedisPhone implements Serializable {

	private static final long serialVersionUID = -3386169721941882770L;

	//手机号码
	private String a;
	
	//发送时间
	private long b;
	
	//发送数量
	private int c;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
}