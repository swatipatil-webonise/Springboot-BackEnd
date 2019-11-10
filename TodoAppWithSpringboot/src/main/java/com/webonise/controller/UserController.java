package com.webonise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.model.User;
import com.webonise.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Sign Up")
	@PostMapping("/register")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
}
