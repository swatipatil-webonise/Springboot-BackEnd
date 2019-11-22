package com.webonise.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.webonise.dao.TodoDao;
import com.webonise.model.Todo;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

	@InjectMocks
	private TodoService todoService;

	@Mock
	private TodoDao todoDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testListAllTodos() {
		List<Todo> list = new ArrayList<>();
		list.add(new Todo(1, "Learn Spring Security Demo"));
		list.add(new Todo(2, "Learn Spring Boot Demo"));
		when(todoDao.findAll()).thenReturn(list);
		List<Todo> todoList = todoService.getAllTodos();
		assertEquals(2, todoDao.count());
		assertEquals(false, todoList.isEmpty());
		assertEquals(true, Optional.ofNullable(todoList).isPresent());
		assertEquals(true, list.equals(todoList));
	}

	@Test
	public void textAddTodo() {
		todoService.addTodo(new Todo(1, "Learn OOPS"));
		assertEquals(true, todoDao.exists(1));
		assertEquals(new Todo(1, "Learn OOPS"), todoDao.findOne(1));
		assertEquals("Learn OOPS", todoDao.findOne(1).getDesc());
	}

	@Test
	public void testUpdateTodo() {
		todoService.updateTodo(3, "Learn ES6");
		assertEquals(true, todoDao.exists(3));
		assertEquals(new Todo(1, "Learn OOPS"), todoDao.findOne(1));
		assertEquals(3, todoDao.findOne(3).getId());
	}

	@Test
	public void testDeleteTodo() {
		todoService.addTodo(new Todo(1, "Learn OOPS"));
		assertEquals(1, todoService.deleteTodo(1));
		assertEquals(0, todoService.deleteTodo(1));
	}
}
