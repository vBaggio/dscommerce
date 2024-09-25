package com.vbaggio.dscommerce.services.exceptions;

public class DatabaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DatabaseException() {
		super("Database failure");
	}
	
	public DatabaseException(String message) {
		super(message);
	}
}
