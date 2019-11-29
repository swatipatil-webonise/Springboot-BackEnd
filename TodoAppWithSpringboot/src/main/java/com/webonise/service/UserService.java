package com.webonise.service;

import com.webonise.model.User;

public interface UserService {
	
	/**
	 * This method checks whether user with given username exists, if yes returns that else throws NotFoundException. 
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username);
	
	/**
	 * This method persists user into User table. 
	 * @param user
	 * @return User
	 */
	public User addUser(User user);
}
