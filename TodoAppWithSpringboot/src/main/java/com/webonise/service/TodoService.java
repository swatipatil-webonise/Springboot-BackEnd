package com.webonise.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.dao.TodoDao;
import com.webonise.exception.EmptyFoundException;
import com.webonise.exception.NotFoundException;
import com.webonise.model.Todo;

@Service
public class TodoService {

	@Autowired
	private TodoDao todoDao;

	private Logger log = LoggerFactory.getLogger(TodoService.class);
	
	public List<Todo> getAllTodos() {
		List<Todo> todoList = todoDao.findAll();
		if(todoList != null && !todoList.isEmpty()) {
			return todoList;
		} else {
			log.error("Empty todolist found.");
			throw new EmptyFoundException("Empty todolist found.");
		}
	}

	public Todo addTodo(Todo todo) {
		return todoDao.save(todo);
	}
	
	public Todo updateTodo(Todo todo) {
		if(todoDao.findById(todo.getId()) != null) {
			todoDao.delete(todo);
			return todoDao.save(todo);
		} else {
			return todoDao.save(todo);
		}
	}

	public int deleteTodo(int id) {
		if(todoDao.findById(id).orElse(null) != null) {
			return todoDao.deleteTodoById(id);
		} else {
			log.error("Requested todo with id {} not found.", id);
			throw new NotFoundException("Requested todo by id not found.");
		} 		
	}
}
