package gritbus.hipchonbackend.exception;

import gritbus.hipchonbackend.error.ErrorCode;
import lombok.Getter;

@Getter
public class ElementDuplicatedException extends RuntimeException{
	private ErrorCode errorCode;

	public ElementDuplicatedException(String msg,ErrorCode errorCode){
		super(msg);
		this.errorCode=errorCode;
	}
}
