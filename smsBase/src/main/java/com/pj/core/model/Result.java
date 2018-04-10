package com.pj.core.model;

import com.pj.core.enums.HttpStatusEnums;

import java.io.Serializable;

public class Result implements Serializable {
	
    private static final long serialVersionUID = 6288374846131788743L;
    
    private String message;

    private int statusCode;
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Result() {}
	
	public Result(String message) {
		this.message = message;
	}
    
    public Result(HttpStatusEnums state) {
    	this.statusCode = state.getStatusCode();
    	this.message = state.getMessage();
    }

	@Override
	public String toString() {
		return "Result [message=" + message + ", statusCode=" + statusCode
				+ "]";
	}
}
