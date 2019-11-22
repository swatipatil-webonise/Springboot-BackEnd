package com.webonise.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.webonise.dao.UserDao;
import com.webonise.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserDao userDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindByUsername(String username) {
		when(userDao.findByUsername("swati"))
				.thenReturn(new com.webonise.model.User("Swati", "swati", "swati", "swati@gmail.com"));
		com.webonise.model.User user = userService.findByUsername("swati");
		assertEquals("Swati", user.getName());
		assertEquals("swati", user.getUsername());
		assertEquals("swati", user.getPassword());
		assertEquals("swati@gmail.com", user.getEmail());
	}

	@Test
	public void testAddUser() {
		User user = new User("Mohan", "mohan", "mohan", "mohan@gmail.com");
		userService.addUser(user);
		verify(userDao, times(1)).save(user);
		assertEquals(true, userDao.exists("mohan"));
	}
}
