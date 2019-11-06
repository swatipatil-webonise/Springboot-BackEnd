package com.webonise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.dao.UserDao;
import com.webonise.exception.EmailAlreadyExistException;
import com.webonise.exception.UserNotInitializedException;
import com.webonise.exception.UsernameAlreadyExistException;
import com.webonise.exception.UsernameNotInitializedException;
import com.webonise.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	private Logger log = LoggerFactory.getLogger(UserService.class);
	
	public User findByUsername(String username) {
		if (username == null) {
			log.error("Username not initialized.");
			throw new UsernameNotInitializedException();
		} 
		User user = userDao.findByUsername(username);
		if (user == null) {
			log.error("User by given username not exists.");
		}
		return user;
	}
	
	public User getUser() {
		return new User((int)userDao.count() + 1);
	}
	
	public User addUser(User user) {
		if(user == null) {
			log.error("User not initilized.");
			throw new UserNotInitializedException();
		} else if (userDao.findByUsername(user.getUsername()) != null) {
			log.error("Username already exists.");
			throw new UsernameAlreadyExistException();
		} else if (userDao.findByEmail(user.getEmail()) != null ) {
			log.error("Email id alredy exists.");
			throw new EmailAlreadyExistException();
		} else {
			return userDao.save(user);
		}
	}
}
