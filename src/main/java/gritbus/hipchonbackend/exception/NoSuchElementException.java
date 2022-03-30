package gritbus.hipchonbackend.exception;

import gritbus.hipchonbackend.error.ErrorCode;
import lombok.Getter;

@Getter
public class NoSuchElementException extends RuntimeException{
	private ErrorCode errorCode;

	public NoSuchElementException(String msg,ErrorCode errorCode){
		super(msg);
		this.errorCode=errorCode;
	}
}
