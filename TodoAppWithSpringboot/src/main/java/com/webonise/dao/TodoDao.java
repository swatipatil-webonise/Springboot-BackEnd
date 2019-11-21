package com.webonise.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.webonise.model.Todo;

@Repository
public interface TodoDao extends JpaRepository<Todo, Integer> {

	@Modifying
	@Transactional
	@Query("delete from Todo where id = :id")
	public int deleteTodoById(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query("update Todo todo set todo.desc = :desc where todo.id = :id")
	public int updateTodo(@Param("id") int id, @Param("desc") String desc);
}
