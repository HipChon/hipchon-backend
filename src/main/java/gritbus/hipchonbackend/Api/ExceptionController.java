package gritbus.hipchonbackend.Api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.error.ErrorResponse;
import gritbus.hipchonbackend.exception.ElementDuplicatedException;
import gritbus.hipchonbackend.exception.NoSuchElementException;
import gritbus.hipchonbackend.exception.NoUserException;
import gritbus.hipchonbackend.exception.UserDuplicatedException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NoUserException.class)
	public ResponseEntity<ErrorResponse> handleUserException(NoUserException ex){
		return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
	}

	@ExceptionHandler(UserDuplicatedException.class)
	public ResponseEntity<ErrorResponse> handleUserDuplicatedException(UserDuplicatedException ex){
		return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
	}

	@ExceptionHandler(ElementDuplicatedException.class)
	public ResponseEntity<ErrorResponse> handleElementDuplicatedException(ElementDuplicatedException ex){
		return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex){
		return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
	}

	// @ExceptionHandler(RuntimeException.class)
	// public ResponseEntity<ErrorResponse> handleException(ErrorCode code){
	// 	ErrorResponse response = new ErrorResponse(code);
	// 	return new ResponseEntity<>(response, HttpStatus.valueOf(code.getStatus()));
	// }

}
