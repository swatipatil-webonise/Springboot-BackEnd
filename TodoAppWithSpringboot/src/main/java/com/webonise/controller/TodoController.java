package com.webonise.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.model.Todo;
import com.webonise.model.User;
import com.webonise.service.TodoService;
import com.webonise.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("/todos")
@Api(value="Todo-Management-System")
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:3000"})
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "View a list of available todos")
	@GetMapping(path = "/all")
	public List<Todo> getAllTodos() throws Exception {
		return todoService.findAll();
	}
	
	@ApiOperation(value = "Get todo by his/her id")
	@GetMapping("/get/{id}")
	public Todo getTodo(@PathVariable("id") int id) throws Exception {
		return todoService.findById(id);
	}
	
	@ApiOperation(value = "Add new todo")
	@PostMapping("/add")
	public Todo addTodo(@RequestBody Todo todo) throws Exception {
		todoService.save(todo);
		return todo;
	}
	
	@ApiOperation(value = "Update existing todo")
	@PutMapping("/update")
	public Todo updateTodo(@RequestBody Todo todo) throws Exception {
		todoService.save(todo);
		return todo;
	}
	
	@ApiOperation(value = "Delete todo by his/her id")
	@DeleteMapping("/delete/{id}")
	public String deleteTodo(@PathVariable int id) throws Exception {
		todoService.deleteById(id);
		return "deleted";
	}
	
	@ApiOperation(value = "Get User by their username")
	@PostMapping("/getUser")
	public String getUser(@RequestBody Map<String, String> json) throws Exception {
		User user = userService.findByUsername(json.get("username"));
		if (user == null) {
			return "RegisterFirst";
		} else if(user.getPassword().equals(json.get("password"))){
			return "Valid";
		} else {
			return "WrongPassword";
		}
	}
	
	@ApiOperation(value = "Get the max id for next user to be inserted")
	@GetMapping("/getMaxUser")
	public User getMaxIdOfUser() throws Exception {
		return userService.getMaxId();
	}
	
	@ApiOperation(value = "Add new user")
	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) {
		return userService.addUser(user);
	}	
}
