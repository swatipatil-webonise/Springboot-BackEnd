package com.webonise;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.webonise.model.Todo;
import com.webonise.service.TodoService;
import com.webonise.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

	@Autowired
	private TodoService todoService;

	@Test
	public void whenTodoIdProvided_thenTodoDescriptionIsCorrect() {
		Mockito.when(todoService.addTodo(new Todo(1, "Learn Java")).getId()).thenReturn(1);
		int id = todoService.addTodo(new Todo(1, "Learn Java")).getId();
		Assert.assertEquals(1, id);
	}
}
