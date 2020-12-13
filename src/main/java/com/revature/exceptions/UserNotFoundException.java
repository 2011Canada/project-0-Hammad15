package com.revature.exceptions;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("User Not Found\nYou will be returned to the main menu.\n\n");
	}
	
	public String getMessage() {
		return "User Not Found\nYou will be returned to the main menu.\n\n";
	}
	

}
