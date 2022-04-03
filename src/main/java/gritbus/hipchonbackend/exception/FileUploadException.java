package gritbus.hipchonbackend.exception;

import gritbus.hipchonbackend.error.ErrorCode;
import lombok.Getter;

@Getter
public class FileUploadException extends RuntimeException{
	private ErrorCode errorCode;

	public FileUploadException(String msg,ErrorCode errorCode){
		super(msg);
		this.errorCode=errorCode;
	}
}
