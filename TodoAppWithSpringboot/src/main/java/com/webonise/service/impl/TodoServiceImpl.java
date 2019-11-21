
package com.webonise.service.impl;

import java.util.List;
import java.util.Optional;
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
		if (Optional.ofNullable(todoList).isPresent()) {
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
		final int RECORD_NOT_UPDATED = 0;
		if (todoDao.updateTodo(id, desc) != RECORD_NOT_UPDATED) {
			return todoDao.findOne(id);
		} else {
			 return todoDao.save(new Todo(id, desc));
		}
	}

	public int deleteTodo(int id) {
		if (Optional.ofNullable(todoDao.findOne(id)).isPresent()) {
			return todoDao.deleteTodoById(id);
		} else {
			log.error("Requested todo with id {} not found.", id);
			throw new NotFoundException("Requested todo by id not found.");
		}
	}	
}
