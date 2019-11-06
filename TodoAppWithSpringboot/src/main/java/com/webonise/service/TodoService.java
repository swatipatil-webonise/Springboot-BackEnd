package com.webonise.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.dao.TodoDao;
import com.webonise.exception.EmptyTodoListFoundException;
import com.webonise.exception.TodoByGivenIdNotExistException;
import com.webonise.exception.TodoNotInitializedException;
import com.webonise.exception.TodosNotExistsException;
import com.webonise.model.Todo;

@Service
public class TodoService {

	@Autowired
	private TodoDao todoDao;

	private Logger log = LoggerFactory.getLogger(TodoService.class);
	
	public List<Todo> findAll() {
		List<Todo> todoList = todoDao.findAll();
		if(todoList == null) {
			log.error("Uninitialized todolist found.");
			throw new TodosNotExistsException(); 
		} else if (todoList.isEmpty()) {
			log.error("Empty todolist found.");
			throw new EmptyTodoListFoundException();
		} 
		return todoList;		
	}

	public Todo save(Todo todo) {
		if(todo == null) {
			log.error("Requested todo not initilized.");
			throw new TodoNotInitializedException();
		} else {
			return todoDao.save(todo);
		}
	}

	public Todo findById(int id) {
		Todo todo = todoDao.findById(id).orElse(null);
		if(todo == null) {
			log.error("Requested todo by id not exists.");
			throw new TodoByGivenIdNotExistException();
		} 
		return todo;
	}

	public Todo deleteById(int id) {
		Todo todo = todoDao.findById(id).orElse(null);
		if(todo == null) {
			log.error("Requested todo by id not exists.");
			throw new TodoByGivenIdNotExistException();
		} else {
			todoDao.delete(todo);
		}
		return todo;
	}
}
