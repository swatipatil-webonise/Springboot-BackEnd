package com.webonise.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.webonise.dao.UserDao;
import com.webonise.model.User;
import com.webonise.service.impl.UserServiceImpl;

public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserDao userDao;

	public UserServiceTest() {
		this.userService = new UserServiceImpl();
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindByUsername() {
		User user = new User("Swati", "swati", "swati", "swati@gmail.com");
		when(userDao.exists(user.getUsername())).thenReturn(true);
		when(userService.findByUsername(user.getUsername())).thenReturn(user);
		assertEquals(user, userService.findByUsername(user.getUsername()));
		assertEquals("swati", user.getUsername());
	}

	@Test
	public void testAddUser() {
		User user = new User("Mohan", "mohan", "mohan", "mohan@gmail.com");
		when(userService.addUser(user)).thenReturn(user);
		assertEquals(user, userService.addUser(user));
		when(userDao.exists(user.getUsername())).thenReturn(true);
		assertEquals(true, userDao.exists(user.getUsername()));
	}
}
