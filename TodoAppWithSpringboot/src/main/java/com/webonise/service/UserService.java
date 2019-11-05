package com.webonise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.dao.UserDao;
import com.webonise.model.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User findByUsername(String username) throws Exception {
		try {
			return userDao.findByUsername(username);
		} catch (Exception ex) {
			log.info("Exception occured during retrival of user by it's username.");
			throw new Exception("Find by username was unable to execute." , ex);
		}
	}
	
	public User getMaxId() throws Exception {
		try {
			return new User((int)userDao.count() + 1);
		} catch (Exception ex) {
			log.info("Exception occured during retrival of max id from user table.");
			throw new Exception("Get max Id was unable to execute." , ex);
		}
	}
	
	public String addUser(User user) {
		try {
			userDao.save(user);
		} catch (Exception ex) {
			if(userDao.findByUsername(user.getUsername()) != null) {
				return "username already exists";
			} else if (userDao.findByEmail(user.getEmail()) != null) {
				return "email already exists";
			}
		}
		return "added";
	}
}
