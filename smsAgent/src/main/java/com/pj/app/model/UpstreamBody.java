package com.pj.app.model;

import java.io.Serializable;

public class UpstreamBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682718490458303370L;
	private String userAccount;
	private String pageSize;
	private String timestamp;
	private String sign;
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
