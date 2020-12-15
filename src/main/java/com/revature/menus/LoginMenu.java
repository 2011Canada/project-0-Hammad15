package com.revature.menus;

import java.util.Scanner;

public class LoginMenu {
	
	public static void display() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome to Hammad's Bank \n\n" 
				+ "Please enter the appropriate number to continue \n"
				+ "1. Login as an employee \n"
				+ "2. Login as a Customer \n"
				+ "3. Register as a Customer");
		
		System.out.print("Your choice: ");
		
		int n = sc.nextInt();
		
		switch (n) {
		
		case 1:
			
			EmployeeLogin.display();
			break;
			
		case 2:
			
			CustomerLogin.display();
			break;
			
		case 3: 
			
			CustomerRegister.display();
			break;
			
			}
		}
	}
