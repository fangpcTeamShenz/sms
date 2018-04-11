package com.pj.core.entity;

import com.pj.core.enums.HttpStatusEnums;

import java.io.Serializable;

public class Result implements Serializable {
	
    private static final long serialVersionUID = 6288374846131788743L;
    
    private String message;

    private String status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Result() {}
	
	public Result(String message) {
		this.message = message;
	}
    
    public Result(HttpStatusEnums status) {
    	this.status = status.getStatus();
    	this.message = status.getMessage();
    }
    
    public Result(String status,String message) {
    	this.status = status;
    	this.message = message;
    }
    
}
