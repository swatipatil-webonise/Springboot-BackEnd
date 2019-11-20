package com.webonise.service;

import java.util.List;
import com.webonise.model.Todo;

public interface TodoService {
	
	/**
	 * This method retrieve all the records from Todo table.
	 * @return List<Todo>
	 */
	public List<Todo> getAllTodos();
	
	/**
	 * This method persist todo object into Todo table.
	 * @param todo
	 * @return Todo
	 */
	public Todo addTodo(Todo todo);
	
	/**
	 * This method checks whether todo by id is present, if yes then updates desc else persist that todo into Todo table.
	 * @param id
	 * @param desc
	 * @return Todo
	 */
	public Todo updateTodo(int id, String desc);
	
	/**
	 * This method checks whether todo by id exists, if yes then deletes else throws an exception.
	 * @param id
	 * @return 1 if todo gets deleted else 0.  
	 */
	public int deleteTodo(int id);
}
