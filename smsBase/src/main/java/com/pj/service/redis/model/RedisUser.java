package com.pj.service.redis.model;

import java.io.Serializable;

public class RedisUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3386169721941882770L;

	//用户名appsecret秘钥
    private String a;

    //联系电话
    private String b;

    //邮箱
    private String c;

    //账户余额（条）
    private Long d;

    //信用条数
    private Long e;

    //余额预警条数
    private Long f;

    //余额预警接收手机号码（多个以，隔开）
    private String g;
    
    private String h;
    
    //上行回掉地址
    private String i;
    
    //回调通知地址
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

	public Long getD() {
		return d;
	}

	public void setD(Long d) {
		this.d = d;
	}

	public Long getE() {
		return e;
	}

	public void setE(Long e) {
		this.e = e;
	}

	public Long getF() {
		return f;
	}

	public void setF(Long f) {
		this.f = f;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
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
    
}