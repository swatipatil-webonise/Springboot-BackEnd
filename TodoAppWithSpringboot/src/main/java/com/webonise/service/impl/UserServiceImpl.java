package com.webonise.service.impl;

import com.webonise.model.User;
import com.webonise.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.webonise.dao.UserDao;
import com.webonise.exception.EmailAlreadyExistException;
import com.webonise.exception.NotFoundException;
import com.webonise.exception.UsernameAlreadyExistException;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public User findByUsername(String username) {
		if (userDao.exists(username)) {
			return userDao.findByUsername(username);
		} else {
			log.error("User with username {} not found", username);
			throw new NotFoundException("User not found.");
		}
	}

	@Override
	public User addUser(User user) {
		if (userDao.exists(user.getUsername())) {
			log.error("Username : {} already exists.", user.getUsername());
			throw new UsernameAlreadyExistException("Username already exist.");
		} else if (userDao.findByEmail(user.getEmail()) != null) {
			log.error("Email id : {} alredy exists.", user.getEmail());
			throw new EmailAlreadyExistException("Email already exist.");
		} else {
			return userDao.save(user);
		}
	}
}
