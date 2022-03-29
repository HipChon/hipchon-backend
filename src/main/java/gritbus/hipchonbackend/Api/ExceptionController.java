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


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		ErrorResponse response = new ErrorResponse(ErrorCode.UNAUTHORIZED_USER);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
