package com.webonise.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.webonise.exception.EmailAlreadyExistException;
import com.webonise.exception.EmptyFoundException;
import com.webonise.exception.ExpiredJwtFoundExcpetion;
import com.webonise.exception.IncorrectUsernameOrPasswordException;
import com.webonise.exception.InvalidTokenFoundException;
import com.webonise.exception.NotFoundException;
import com.webonise.exception.UnauthorizedUserFoundException;
import com.webonise.exception.UsernameAlreadyExistException;

@ControllerAdvice
public class TodoAppExceptionController {

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> notFoundException(NotFoundException ex) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = EmptyFoundException.class)
	public ResponseEntity<Object> emptyFoundException(EmptyFoundException ex) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(value = EmailAlreadyExistException.class)
	public ResponseEntity<Object> emailAlreadyExistException(EmailAlreadyExistException ex) {
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = UsernameAlreadyExistException.class)
	public ResponseEntity<Object> usernameAlreadyExistException(UsernameAlreadyExistException ex) {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = IncorrectUsernameOrPasswordException.class)
	public ResponseEntity<Object> incorrectUsernameOrPasswordException(IncorrectUsernameOrPasswordException ex) {
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = UnauthorizedUserFoundException.class)
	public ResponseEntity<Object> unauthorizedUserFoundException(UnauthorizedUserFoundException ex) {
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = InvalidTokenFoundException.class)
	public ResponseEntity<Object> invalidTokenFoundException(InvalidTokenFoundException ex) {
		return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}
	
	@ExceptionHandler(value = ExpiredJwtFoundExcpetion.class)
	public ResponseEntity<Object> expiredJwtFoundExcpetion(ExpiredJwtFoundExcpetion ex) {
		return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}
}
