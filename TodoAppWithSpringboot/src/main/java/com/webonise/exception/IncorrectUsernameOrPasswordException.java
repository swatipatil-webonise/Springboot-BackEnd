package com.webonise.exception;

public class IncorrectUsernameOrPasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public IncorrectUsernameOrPasswordException(String message) {
		super(message);
	}
}
