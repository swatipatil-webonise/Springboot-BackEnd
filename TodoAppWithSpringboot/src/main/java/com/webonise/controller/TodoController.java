package com.webonise.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
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
import com.webonise.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Todo-Management-System")
@CrossOrigin(allowedHeaders = "*", origins = ("*"))
@RequestMapping(path = "/todo-jobs", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@ApiOperation(value = "View list of available todos")
	@GetMapping("/")
	public List<Todo> getTodos() {
		return todoService.getAllTodos();
	}
	
	@ApiOperation(value = "View page of todos")
	@GetMapping("/{pageNumber}")
	public Page<Todo> getTodos(@PathVariable int pageNumber) {
		return todoService.getTodos(pageNumber);
	}

	@ApiOperation(value = "Add new todo")
	@PostMapping("/")
	public Todo addTodo(@RequestBody Todo todo) {
		return todoService.addTodo(todo);
	}

	@ApiOperation(value = "Update existing todo")
	@PutMapping("/")
	public Todo updateTodo(@RequestBody Todo todo) {
		return todoService.updateTodo(todo.getId(), todo.getDesc());
	}

	@ApiOperation(value = "Delete todo by it's id")
	@DeleteMapping("/{id}")
	public int deleteTodo(@PathVariable int id) {
		return todoService.deleteTodo(id);
	}
}
