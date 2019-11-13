package com.webonise.security;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webonise.service.UserService;
import com.webonise.service.impl.UserServiceImpl;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.webonise.model.User user = userService.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
	
	@Bean
	public UserServiceImpl getUserServiceImpl() {
		return new UserServiceImpl();
	}
}
