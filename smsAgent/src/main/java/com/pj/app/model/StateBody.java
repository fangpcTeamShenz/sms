package com.pj.app.model;

import java.io.Serializable;

public class StateBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5732658745371903184L;

	private String outOrderId;
	
	private String phoneNumber;

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
