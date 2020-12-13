package com.revature.exceptions;

public class InternalErrorException extends Exception {
	public InternalErrorException() {
		super("OOPS, something went wrong");
	}
	
	public String getMessage() {
		return "OOPS, something went wrong.\n\n";
	}
}
