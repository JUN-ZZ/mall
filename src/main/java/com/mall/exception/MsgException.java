package com.mall.exception;

public class MsgException extends Exception {

	public MsgException(){
		super();
	}
	
	public MsgException(String message,Throwable cause){
		super(message, cause);
	}
	
	public MsgException(String message){
		super(message);
	}
	
}
