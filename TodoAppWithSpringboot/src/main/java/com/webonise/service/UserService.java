package com.webonise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.dao.UserDao;
import com.webonise.exception.EmailAlreadyExistException;
import com.webonise.exception.NotFoundException;
import com.webonise.exception.UsernameAlreadyExistException;
import com.webonise.model.LoginCredential;
import com.webonise.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	private Logger log = LoggerFactory.getLogger(UserService.class);

	public Boolean isValidUser(LoginCredential credentials) {
		if (userDao.existsById(credentials.getUsername())) {
			if(userDao.findByUsername(credentials.getUsername()).getPassword().equals(credentials.getPassword())) {
				return true;
			} else {
				return false;
			}
		} else {
			log.error("User with username {} not found.", credentials.getUsername());
			throw new NotFoundException("User with given username not found.");
		}
	}	

	public User addUser(User user) {
		if (userDao.existsById(user.getUsername())) {
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
