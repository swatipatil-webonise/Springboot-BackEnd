package com.webonise.exception;

public class EmptyFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmptyFoundException(String message) {
		super(message);
	}
}
