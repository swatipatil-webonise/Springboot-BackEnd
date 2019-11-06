package com.webonise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webonise.model.Todo;

@Repository
public interface TodoDao extends JpaRepository<Todo, Integer>{
	
}
