package com.pj.app.model;

import java.io.Serializable;

/**
 * 账单返回
 * @author Fangpc
 *
 */
public class BilRet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5469934450747310547L;
	
	private String userName;
	
	private String balance;


	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
