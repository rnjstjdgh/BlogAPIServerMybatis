package me.soungho.BlogAPIServer.CustomError;

import lombok.Data;

@Data
public class PostError {
	
	private String errorMsg;
	
	public PostError(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
