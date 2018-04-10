package com.pj.service.redis.model;

import java.io.Serializable;

import com.pj.core.util.MD5Util;

/**
 * 签名实体
 * @author Fangpc
 *
 */
public class RedisSmsSignature implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8525738985450152289L;

	//免审状态下的走的通道, 1-行业通道,2-营销通道
	private Integer a;

	//产品类型, 1-普通,3-签名免审
    private Integer b;

    //签名内容
//    private Integer c;

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}
	
}
