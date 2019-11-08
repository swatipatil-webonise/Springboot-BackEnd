package com.webonise.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.webonise.exception.EmailAlreadyExistException;
import com.webonise.exception.EmptyFoundException;
import com.webonise.exception.NotFoundException;
import com.webonise.exception.UsernameAlreadyExistException;

@ControllerAdvice
public class TodoAppExceptionController {

	@ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Data not found.", HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = EmptyFoundException.class)
    public ResponseEntity<Object> emptyFoundException(EmptyFoundException ex) {
        return new ResponseEntity<>("Empty data found.", HttpStatus.NO_CONTENT);
    }
	
	@ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<Object> emailAlreadyExistException(EmailAlreadyExistException ex) {
        return new ResponseEntity<>("Email already exist.", HttpStatus.NOT_ACCEPTABLE);
    }
	
	@ExceptionHandler(value = UsernameAlreadyExistException.class)
    public ResponseEntity<Object> usernameAlreadyExistException(UsernameAlreadyExistException ex) {
        return new ResponseEntity<>("Username already exist.", HttpStatus.CONFLICT);
    }
}
