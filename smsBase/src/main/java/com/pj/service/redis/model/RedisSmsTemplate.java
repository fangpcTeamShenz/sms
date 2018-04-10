package com.pj.service.redis.model;

import java.io.Serializable;

/**
 * redis里面存储的模板
 * 前期从服务器拿, 后期存本地
 * @author Fangpc
 *
 */
public class RedisSmsTemplate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3747898834327607347L;
	
	//1-行业通道,2-营销通道
    private Integer a;

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

}
