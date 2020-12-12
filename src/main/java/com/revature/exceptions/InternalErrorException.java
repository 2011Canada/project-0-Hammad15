package com.revature.exceptions;

public class InternalErrorException extends Exception {
	public InternalErrorException() {
		super("OOPS, something went wrong");
	}
}
