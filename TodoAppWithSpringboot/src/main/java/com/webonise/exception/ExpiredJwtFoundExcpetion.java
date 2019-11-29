package com.webonise.exception;

public class ExpiredJwtFoundExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExpiredJwtFoundExcpetion(String message) {
		super(message);
	}	
}
