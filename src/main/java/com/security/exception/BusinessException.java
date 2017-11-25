package com.security.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -3877632972829688761L;

	private String message;

	public BusinessException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
