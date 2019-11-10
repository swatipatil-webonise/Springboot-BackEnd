package com.webonise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webonise.model.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	User findByUsername(String username);

	User findByEmail(String email);
}
