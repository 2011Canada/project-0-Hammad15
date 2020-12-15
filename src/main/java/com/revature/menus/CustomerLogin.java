package com.revature.menus;

import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.services.UserServices;

public class CustomerLogin {
	
	public static void display() {

		Customer cust = new Customer();
		
		CustomerDAO custDAO = new CustomerPostgresDAO();		
		
		String username;
		
		String password;
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
	
		System.out.println("Customer Portal \n\n" +
							"Please enter username and password \n");
		System.out.print("Username: ");
		username = sc.next();
		System.out.print("Password: ");
		password = sc.next();
		
		try {
			cust = custDAO.findCustomerByUsernameAndPassword(username, password);
		} catch (UserNotFoundException e) {
			e.getMessage();
			System.out.println("User Not Found\nYou will be returned to the main menu.\n\n");
			return;
		} catch (InternalErrorException e) {
			e.getMessage();
			System.out.println("OOPS, something went wrong.\n\n");
			return;
		}
		
		//app.setUsername(cust.getUsername());
		
		while (!exit) {
			
			System.out.println("Please select what would you like to do next:\n" +
								"1. View your bank account\n" +
								"2. Create a bank account\n" +
								"3. Exit");

			System.out.print("Your choice: ");

			int x = sc.nextInt();
			
			switch (x) {
			case 1:
				
				UserServices.viewBankAccount(cust.getUsername());
				break;
				
			case 2:
				
				UserServices.createBankAccount(cust.getUsername());
				break;
				
			case 3:
				exit = true;
				break;
				default:
					System.out.println("Invalid input");
					}
			}
		}
	}
