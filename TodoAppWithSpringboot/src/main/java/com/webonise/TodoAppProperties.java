package com.webonise;

import org.springframework.stereotype.Component;

@Component
public class TodoAppProperties {
	
	private final String SECRET_KEY = "secret";
	
	public String getSigningKey() {
		return this.SECRET_KEY;
	}
}
