package com.webonise.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.webonise.dao.TodoDao;
import com.webonise.model.Todo;
import com.webonise.service.impl.TodoServiceImpl;

public class TodoServiceTest {

	@InjectMocks
	private TodoService todoService;

	@Mock
	private TodoDao todoDao;

	public TodoServiceTest() {
		this.todoService = new TodoServiceImpl();
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllTodos() {
		List<Todo> list = Arrays.asList(new Todo(1, "Learn Spring Security Demo"),
				new Todo(2, "Learn Spring Boot Demo"));
		when(todoDao.findAll()).thenReturn(list);
		assertEquals(list, todoService.getAllTodos());
	}

	@Test
	public void textAddTodo() {
		Todo todo = new Todo(1, "Learn OOPS");
		when(todoService.addTodo(todo)).thenReturn(todo);
		assertEquals(todo, todoService.addTodo(todo));
		when(todoDao.findOne(1)).thenReturn(todo);
		assertEquals(1, todoDao.findOne(1).getId());
		assertEquals("Learn OOPS", todoDao.findOne(1).getDesc());
	}

	@Test
	public void testUpdateTodo() {
		Todo todo = new Todo(3, "Learn ES6");
		when(todoService.updateTodo(3, "Learn ES6")).thenReturn(todo);
		when(todoDao.findOne(3)).thenReturn(todo);
		assertEquals(todo, todoDao.findOne(3));
		assertEquals(3, todoDao.findOne(3).getId());
		assertEquals("Learn ES6", todoDao.findOne(3).getDesc());
	}

	@Test
	public void testDeleteTodo() {
		Todo todo = new Todo(4, "Learn React JS");
		when(todoDao.findOne(4)).thenReturn(todo);
		when(todoDao.deleteTodoById(4)).thenReturn(1);
		assertEquals(1, todoService.deleteTodo(4));
	}

	@Test
	public void testGetTodos() {
		Page<Todo> page = new PageImpl<Todo>(
				Arrays.asList(new Todo(1, "Learn ES"), new Todo(2, "Learn JS"), new Todo(3, "Learn OOPS")));
		when(todoDao.findAll(new PageRequest(0, 3, new Sort(new Sort.Order(Direction.ASC, "id"))))).thenReturn(page);
		assertEquals(page, todoDao.findAll(new PageRequest(0, 3, new Sort(new Sort.Order(Direction.ASC, "id")))));
	}
}
