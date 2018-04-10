package com.pj.app.model;

import java.io.Serializable;

public class StateRoot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6325038134439649080L;

	private String phoneNumber;
	
	private String	sendStatus;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	
	
}
