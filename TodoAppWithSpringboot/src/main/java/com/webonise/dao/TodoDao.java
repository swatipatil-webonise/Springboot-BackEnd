package com.webonise.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.webonise.model.Todo;

@Repository
public interface TodoDao extends JpaRepository<Todo, Integer>{
	
	@Modifying
    @Transactional
    @Query("delete from Todo where id = ?1")
    public int deleteTodoById(int id);
}
