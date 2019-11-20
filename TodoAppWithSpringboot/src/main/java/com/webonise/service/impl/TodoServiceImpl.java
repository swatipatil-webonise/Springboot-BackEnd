
package com.webonise.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.dao.TodoDao;
import com.webonise.exception.EmptyFoundException;
import com.webonise.exception.NotFoundException;
import com.webonise.model.Todo;
import com.webonise.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	private TodoDao todoDao;

	private Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);

	public List<Todo> getAllTodos() {
		List<Todo> todoList = todoDao.findAll();
		if (todoList != null && !todoList.isEmpty()) {
			return todoList;
		} else {
			log.error("Empty todolist found.");
			throw new EmptyFoundException("Empty todolist found.");
		}
	}

	public Todo addTodo(Todo todo) {
		return todoDao.save(todo);
	}

	public Todo updateTodo(int id, String desc) {
		if (todoDao.updateTodo(id, desc) != 0) {
			return todoDao.findOne(id);
		} else {
			 return todoDao.save(new Todo(id, desc));
		}
	}

	public int deleteTodo(int id) {
		if (todoDao.findOne(id) != null) {
			return todoDao.deleteTodoById(id);
		} else {
			log.error("Requested todo with id {} not found.", id);
			throw new NotFoundException("Requested todo by id not found.");
		}
	}	
}
