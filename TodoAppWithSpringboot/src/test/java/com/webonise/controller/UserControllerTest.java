package com.webonise.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.webonise.model.User;
import com.webonise.security.WebConfig;
import com.webonise.service.UserService;
import com.webonise.service.impl.UserServiceImpl;
import org.springframework.web.context.WebApplicationContext;

public class UserControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	
	@Test
	public void testAddUser() throws Exception {
		User user = new User("Swati", "swati", "swati", "swati@gmail.com");
		this.mockMvc.perform(post("/register").requestAttr("user", user)).andExpect(status().isOk());
				//.andExpect(status().isOk());
//				.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect((ResultMatcher) jsonPath("$.name", "Ajit"));
	}
}
