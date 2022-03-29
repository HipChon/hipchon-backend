package gritbus.hipchonbackend.exception;

import gritbus.hipchonbackend.error.ErrorCode;
import lombok.Getter;

@Getter
public class NoUserException extends RuntimeException{
	private ErrorCode errorCode;

	public NoUserException(String msg,ErrorCode errorCode){
		super(msg);
		this.errorCode=errorCode;
	}
}
