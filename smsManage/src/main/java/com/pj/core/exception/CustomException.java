package com.pj.core.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message) {
		super(message);
	}
	
}
