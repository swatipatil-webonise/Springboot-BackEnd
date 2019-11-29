package com.webonise.exception;

public class InvalidTokenFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidTokenFoundException(String message) {
		super(message);
	}
}
