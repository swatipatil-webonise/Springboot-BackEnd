package com.webonise.exception;

public class UsernameAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistException(String message) {
		super(message);
	}
}
