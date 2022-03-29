package gritbus.hipchonbackend.Api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gritbus.hipchonbackend.error.ErrorCode;
import gritbus.hipchonbackend.error.ErrorResponse;
import gritbus.hipchonbackend.exception.NoUserException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NoUserException.class)
	public ResponseEntity<ErrorResponse> handleNoUserException(NoUserException ex){
		ErrorResponse response = new ErrorResponse(ex.getErrorCode());
		return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
	}

	// @ExceptionHandler(RuntimeException.class)
	// public ResponseEntity<ErrorResponse> handleException(ErrorCode code){
	// 	ErrorResponse response = new ErrorResponse(code);
	// 	return new ResponseEntity<>(response, HttpStatus.valueOf(code.getStatus()));
	// }

}
