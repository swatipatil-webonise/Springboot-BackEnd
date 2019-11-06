package com.webonise.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.webonise.model.Todo;
import com.webonise.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Todo-Management-System")
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:3000"})
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@ApiOperation(value = "View a list of available todos")
	@GetMapping(path = "/todos")
	public List<Todo> getAllTodos() {
		return todoService.findAll();
	}
	
	@ApiOperation(value = "Get todo by his/her id")
	@GetMapping("/todos/{id}")
	public Todo getTodo(@PathVariable("id") int id) {
		return todoService.findById(id);
	}
	
	@ApiOperation(value = "Add new todo")
	@PostMapping("/todos")
	public Todo addTodo(@RequestBody Todo todo) {
		return todoService.save(todo);
	}
	
	@ApiOperation(value = "Update existing todo")
	@PutMapping("/todos")
	public Todo updateTodo(@RequestBody Todo todo) {
		todoService.deleteById(todo.getId());
		return todoService.save(todo);
	}
	
	@ApiOperation(value = "Delete todo by his/her id")
	@DeleteMapping("/todos/{id}")
	public Todo deleteTodo(@PathVariable int id) {
		return todoService.deleteById(id);
	}
}
