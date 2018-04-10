package com.pj.service.redis.model;

import java.io.Serializable;

/**
 * 对接模块redis实体(不可以修改)
 * @author Fangpc
 *
 */
public class RedisModule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2220458294847755896L;

    //[作废,还是走http协议,考虑到后期量大]身份码,2位字母,代表MQ消息分类
    private String a;
    
    //余额,根据对方协议端口获取,50之一几率写
    private String b;

    //对接模块访问地址
    private String c;
    
    //对接模块签名密钥
    private String d;
    
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

}