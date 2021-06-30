package me.soungho.BlogAPIServer.CustomException;

public class PostValidationException extends RuntimeException{
	public PostValidationException(String msg){
		super(msg);
	}
}