package com.webonise.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.webonise.dao.TodoDao;
import com.webonise.model.Todo;
import com.webonise.service.TodoService;
import com.webonise.service.impl.TodoServiceImpl;

public class TodoControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private TodoService todoService;
	
	@Mock
	private TodoDao todoDao;
		
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new TodoController()).build();
		this.todoService = new TodoServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void textGetTodos() throws Exception {
		List<Todo> todoList = Arrays.asList(new Todo(1, "Learn Java"), new Todo(2, "Learn JS"));
		when(todoDao.findAll()).thenReturn(todoList);
		assertEquals(todoList, todoDao.findAll());
	    when(todoService.getAllTodos()).thenReturn(todoList);
	   	this.mockMvc.perform(get("/todo-jobs/").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].desc", is("Learn Java")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].desc", is("Learn JS")));
	}

	@Test
	public void testAddTodo() throws Exception {
		when(todoService.addTodo(new Todo(1, "Learn Java"))).thenReturn(new Todo(1, "Learn Java"));
		mockMvc.perform((RequestBuilder) ((ResultActions) post("/todo-jobs/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.desc", is("Learn Java"))));
	}

	@Test
	public void testUpdateTodo() throws Exception {
		when(todoService.updateTodo(2, "Learn Javascript")).thenReturn(new Todo(2, "Learn Javascript"));
		mockMvc.perform((RequestBuilder) ((ResultActions) put("/todo-jobs/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.desc", is("Learn Javascript"))));
	}

	@Test
	public void testDeleteTodo() throws Exception {
		when(todoService.updateTodo(2, "Learn Javascript")).thenReturn(new Todo(1, "Learn Javascript"));
		mockMvc.perform((RequestBuilder) ((ResultActions) delete("/todo-jobs/{id}")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$", is(1))));
	}
}
