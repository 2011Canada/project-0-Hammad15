package com.revature.menus;

import com.revature.services.UserServices;

public class CustomerRegister {
	
	public static void display() {
				
		//boolean exit = false;	
		
		System.out.println("Thank you for choosing the best bank in the world! \n\n" +
							"Please enter your first name and last name. \n" +
							"Please create a username and password. \n");
		UserServices.customerRegistration();
	}

}
