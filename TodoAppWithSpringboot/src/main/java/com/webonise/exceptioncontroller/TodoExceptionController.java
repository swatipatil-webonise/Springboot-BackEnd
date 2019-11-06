package com.webonise.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.webonise.exception.EmptyTodoListFoundException;
import com.webonise.exception.TodoByGivenIdNotExistException;
import com.webonise.exception.TodoNotInitializedException;
import com.webonise.exception.TodosNotExistsException;

@ControllerAdvice
public class TodoExceptionController {

	@ExceptionHandler(value = TodosNotExistsException.class)
    public ResponseEntity<Object> todosNotExistsException(TodosNotExistsException ex) {
        return new ResponseEntity<>("Todos not found.", HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = TodoByGivenIdNotExistException.class)
    public ResponseEntity<Object> todoByGivenIdNotExistException(TodoByGivenIdNotExistException ex) {
        return new ResponseEntity<>("Todo by given id not found.", HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = EmptyTodoListFoundException.class)
    public ResponseEntity<Object> emptyTodoListFoundException(EmptyTodoListFoundException ex) {
        return new ResponseEntity<>("Empty todo list found.", HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = TodoNotInitializedException.class)
    public ResponseEntity<Object> todoNotInitializedException(TodoNotInitializedException ex) {
        return new ResponseEntity<>("Todo not initilized.", HttpStatus.NO_CONTENT);
    }	
}
