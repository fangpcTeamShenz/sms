package com.pj.app.model;

import java.io.Serializable;

public class UpstreamRet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7643910138143993419L;
	private String phone;
	private String message;
	private String receiveTime;
	private String suffix;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
}
