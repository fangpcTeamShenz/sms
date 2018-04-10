package com.pj.core.model;

import java.io.Serializable;

public class JSONRequest<T> implements Serializable{
	
	private static final long serialVersionUID = 8835455661140554309L;
	
	private String username;
	private String sign;
	private String timestamp;
	private Integer pageNo;
	private Integer pageSize;
	private T paras;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public T getParas() {
		return paras;
	}
	public void setParas(T paras) {
		this.paras = paras;
	}
	
}
