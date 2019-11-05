package com.webonise.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webonise.dao.TodoDao;
import com.webonise.model.Todo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoService {

	@Autowired
	private TodoDao todoDao;

	public List<Todo> findAll() throws Exception {
		try {
			return (List<Todo>) todoDao.findAll();
		} catch (NullPointerException ex) {
			log.info("Todos are not selected");
			throw new NullPointerException("Todo list is not initialized properly.");
		} catch (Exception ex) {
			log.info("Exception occured while selecting all todos from database.");
			throw new Exception("FindAll was unable to excute.", ex);
		}
	}

	public void save(Todo todo) throws Exception {
		try {
			todoDao.save(todo);
		} catch (Exception ex) {
			log.info("Exception occured while inserting todo into database.");
			throw new Exception("Save is unable to execute.", ex);
		}
	}

	public Todo findById(int id) throws Exception  {
		try {
			return todoDao.findById(id).orElse(null);
		} catch (Exception ex) {
			log.info("Exception occured while selecting single todo from database.");
			throw new Exception("FindById was unable to execute.", ex);
		}
	}

	public void deleteById(int id) throws Exception {
		try {
			todoDao.deleteById(id);
		} catch (Exception ex) {
			log.info("Exception occured while deleting todo from database.");
			throw new Exception("DeleteById was unable to execute.", ex);
		}
	}

	public int getMax() throws Exception   {
		try {
			return (int) todoDao.count();
		} catch (Exception ex) {
			log.info("Exception occured while getting total count from todo table.");
			throw new Exception("Check the total number of records in todo table.", ex);
		}
	}
}
