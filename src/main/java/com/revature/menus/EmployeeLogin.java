package com.revature.menus;

import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.services.EmployeeServices;

public class EmployeeLogin {
	
	public static void display() {
		
		String username;
		
		String password;
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;

		System.out.println("Employee Portal. \n\n" +
							"Please enter username and password \n");
		System.out.print("Username: ");
		username = sc.next();
		System.out.print("Password: ");
		password = sc.next();
		
		while (!exit) {
			
		System.out.println("Please select what would you like to do next:\n" +
							"1. View a pending application\n" +
							"2. View all bank accounts\n" +
							"3. View all transactions\n" +
							"4. Exit");
		
		System.out.print("Your choice: ");
		
		int x = sc.nextInt();
		
		switch (x) {
		case 1: 
			
			EmployeeServices.viewPendingApplication();
			break;
			
		case 2:
			
			EmployeeServices.viewAllAccounts();
			break;
			
		case 3:
			
			EmployeeServices.viewAllTransactions();
			break;
			
		case 4: 
			exit = true;
			break;
		}
		  }
		
	}

}
