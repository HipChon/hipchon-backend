package gritbus.hipchonbackend.exception;

import gritbus.hipchonbackend.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserDuplicatedException extends RuntimeException{
	private ErrorCode errorCode;

	public UserDuplicatedException(String msg,ErrorCode errorCode){
		super(msg);
		this.errorCode=errorCode;
	}
}
