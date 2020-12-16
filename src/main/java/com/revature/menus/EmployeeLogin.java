package com.revature.menus;

import java.util.Scanner;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankAppLauncher;
import com.revature.models.Employee;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.services.EmployeeServices;

public class EmployeeLogin {
	
	public static void display() {
		
		EmployeeServices es = new EmployeeServices();
		
		Employee emp = new Employee();
		
		CustomerDAO custDAO = new CustomerPostgresDAO();
		
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
		
		try {
			emp = custDAO.findEmployeeByUsernameAndPassword(username, password);
		} catch (UserNotFoundException e) {
			e.getMessage();
			System.out.println("User Not Found\nYou will be returned to the main menu.\n\n");
			return;
		} catch (InternalErrorException e) {
			e.getMessage();
			System.out.println("OOPS, something went wrong.\n\n");
			return;
		}
		
		BankAppLauncher.bankLogger.debug("An employee logged in.");
		
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
			
			es.viewAllAccounts();
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
