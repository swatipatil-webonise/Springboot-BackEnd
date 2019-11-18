package com.webonise.service;

import java.util.List;

import com.webonise.model.Todo;

public interface TodoService {
	public List<Todo> getAllTodos();
	public Todo addTodo(Todo todo);
	public Todo updateTodo(Todo todo);
	public int deleteTodo(int id);
}
