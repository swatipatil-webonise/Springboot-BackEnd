package com.webonise.service;

import com.webonise.model.User;

public interface UserService {
	public User findByUsername(String username);
	public User addUser(User user);
}
