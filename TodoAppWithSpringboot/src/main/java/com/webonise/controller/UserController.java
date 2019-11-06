package com.webonise.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.model.User;
import com.webonise.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:3000"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Get User by their username")
	@PostMapping("/users")
	public HttpStatus getUser(@RequestBody Map<String, String> json) throws Exception {
		User user = userService.findByUsername(json.get("username"));
		if(user == null){
			return HttpStatus.NOT_FOUND;
		} else if(user.getPassword().equals(json.get("password"))) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_ACCEPTABLE;
		}
	}
		
	@ApiOperation(value = "Get user")
	@GetMapping("/users")
	public User getUser() throws Exception {
		return userService.getUser();
	}
	
	@ApiOperation(value = "Add new user")
	@PutMapping("/users")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}	
}
