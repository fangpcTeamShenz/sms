package com.pj.app.model;

import java.io.Serializable;

public class StateRet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7977184585173828996L;
	private StateBody businessBody;
    private String userAccount;
    private String sign;
    private String timestamp;
	public StateBody getBusinessBody() {
		return businessBody;
	}
	public void setBusinessBody(StateBody businessBody) {
		this.businessBody = businessBody;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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
    
    
	
}
