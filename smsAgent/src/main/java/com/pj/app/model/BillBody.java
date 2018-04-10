package com.pj.app.model;

import java.io.Serializable;

/**
 * 账单查询
 * @author Fangpc
 *
 */
public class BillBody implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8090999864633948487L;
	private String sign;
	private String userAccount;
	private String timestamp;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
