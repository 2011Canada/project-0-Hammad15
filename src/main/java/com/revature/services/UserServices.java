package com.revature.services;

import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.InternalErrorException;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerPostgresDAO;

public class UserServices {
	
	public static void viewBankAccount(String username) {
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		Account acc = new Account();
		
		Customer cust = new Customer();
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
		
		try {
			acc = accDAO.viewAccount(username);
			System.out.println(acc);
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Account Not Found\nPlease submit an application to open a bank account.\n\n");
			e.printStackTrace();
			return;
		} catch (InternalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		while (!exit) {
		System.out.println("Please select what would you like to do next:\n" +
				"1. Deposit money in to your account\n" +
				"2. Withdraw money from your account\n" +
				"3. Transfer money to someone else's account\n" +
				"4. Exit");
		
		System.out.print("Your choice: ");
		
		int y = sc.nextInt();
		
		switch (y) {
		case 1:
			System.out.println("Please enter the amount you want to deposit");
			System.out.print("Amount: ");
			int deposit = sc.nextInt();
			accDAO.deposit(cust.getUsername(), deposit, acc.getAccountNumber());
			break;
		case 2:
			System.out.println("Please enter the amount you want to withdraw");
			System.out.print("Amount: ");
			int withdraw = sc.nextInt();
			accDAO.withdraw(cust.getUsername(), withdraw, acc.getAccountNumber());
			break;
		case 3:
			System.out.println("Please enter the amount you want to transfer the account number of the recipient");
			System.out.print("Amount: ");
			int transfer = sc.nextInt();
			System.out.print("Account Number: ");
			int accountNum = sc.nextInt();
			accDAO.postTransfer(cust.getUsername(), transfer, accountNum);
			break;
		case 4:
			exit = true;
			break;
			default:
				System.out.println("Invalid input");
				}
		}
		exit = false;
		
	}
	
	public static void createBankAccount(String username) {
		
		Application app = new Application();
		
		CustomerDAO custDAO = new CustomerPostgresDAO();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter the following details to apply for a bank account");
		System.out.print("Initial Deposit: ");
		int starting_balance = sc.nextInt();
		System.out.print("Credit Score: ");
		int credit_score = sc.nextInt();
		System.out.print("Yearly Salary: ");
		int yearly_salary = sc.nextInt();
		
		app = custDAO.accountApplication(username, starting_balance, credit_score, yearly_salary);
		
	}
	
	public static void customerRegistration() {
		
		CustomerDAO custDAO = new CustomerPostgresDAO();
		
		String username;
		
		String password;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("First Name: ");
		String firstName = sc.next();
		System.out.print("Last Name: ");
		String lastName = sc.next();
		System.out.print("Username: ");
		username = sc.next();
		
		if (custDAO.getAllUsernames().contains(username)) {
			System.out.println("The entered username is already in use, please choose a different one. " +
								"You will now be going back to the main menu");
			return;
		}
		
		System.out.print("Password: ");
		password = sc.next();
		
		custDAO.makeNewCustomer(firstName, lastName, username, password);
		
	}
	
	public static void depositMoney() {
		
		
		
	}
	
	public static void withdrawMoney() {
		
		
		
	}
	
	public static void sendMoney() {
		
		
		
	}
	
	public static void recieveMoney() {
		
		
		
	}

}
