package com.webonise.exception;

public class UnauthorizedUserFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnauthorizedUserFoundException(String message) {
		super(message);
	}
}
