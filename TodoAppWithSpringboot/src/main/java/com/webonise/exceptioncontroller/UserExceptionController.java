package com.webonise.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.webonise.exception.EmailAlreadyExistException;
import com.webonise.exception.UserNotExistException;
import com.webonise.exception.UserNotInitializedException;
import com.webonise.exception.UsernameAlreadyExistException;
import com.webonise.exception.UsernameNotInitializedException;

@ControllerAdvice
public class UserExceptionController {
	
	@ExceptionHandler(value = UsernameNotInitializedException.class)
    public ResponseEntity<Object> usernameNotInitializedException(UsernameNotInitializedException ex) {
        return new ResponseEntity<>("Username not initialized.", HttpStatus.NO_CONTENT);
    }
	
	@ExceptionHandler(value = UserNotExistException.class)
    public ResponseEntity<Object> userNotExistException(UserNotExistException ex) {
        return new ResponseEntity<>("User not exist.", HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value = UserNotInitializedException.class)
    public ResponseEntity<Object> userNotInitializedException(UserNotInitializedException ex) {
        return new ResponseEntity<>("User not initialized.", HttpStatus.NO_CONTENT);
    }
	
	@ExceptionHandler(value = UsernameAlreadyExistException.class)
    public ResponseEntity<Object> usernameAlreadyExistException(UsernameAlreadyExistException ex) {
        return new ResponseEntity<>("Username already exists..", HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<Object> emailAlreadyExistException(EmailAlreadyExistException ex) {
        return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
    }
}
