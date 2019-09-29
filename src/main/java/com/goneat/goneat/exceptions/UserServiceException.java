package com.goneat.goneat.exceptions;

public class UserServiceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3493864565759971337L;

	public UserServiceException(String errorMessage)
	{
		super(errorMessage);
	}

}
