package com.revature.services;

import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.launcher.BankAppLauncher;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerPostgresDAO;

public class UserServices {
	
	public static void viewBankAccount(String username) {
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		Account acc = new Account();
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
		
		try {
			acc = accDAO.viewAccount(username);
			System.out.println(acc);
		} catch (AccountNotFoundException e) {
			System.out.println("Account Not Found\nPlease submit an application to open a bank account.\n\n");
			e.getMessage();
			return;
		} catch (InternalErrorException e) {
			e.getMessage();
			System.out.println("OOPS, something went wrong.\n\n");
			return;
		}
		while (!exit) {
		System.out.println("Please select what would you like to do next:\n" +
				"1. Deposit money in to your account\n" +
				"2. Withdraw money from your account\n" +
				"3. Send money to someone\n" +
				"4. Receive money from someone\n " +
				"5. Exit");
		
		System.out.print("Your choice: ");
		
		int y = sc.nextInt();
		
		switch (y) {
		case 1:
			
			UserServices.depositMoney(acc.getUsername(), acc.getAccountNumber());
			break;
			
		case 2:
			
			UserServices.withdrawMoney(acc.getUsername(), acc.getAccountNumber());
			break;
			
		case 3:
			
			UserServices.sendMoney(acc.getUsername(), acc.getAccountNumber());
			break;
		
		case 4:
			
			UserServices.receiveMoney(acc.getAccountNumber());
			break;
		
		case 5:
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
		
		BankAppLauncher.bankLogger.debug("A customer account was created.");
		
	}
	
	public static void depositMoney(String username, int accountNumber) {
		
		Scanner sc = new Scanner(System.in);
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		System.out.println("Please enter the amount you want to deposit");
		System.out.print("Amount: ");
		int deposit = sc.nextInt();
		accDAO.deposit(username, deposit, accountNumber);
		
	}
	
	public static void withdrawMoney(String username, int accountNumber) {
		
		Scanner sc = new Scanner(System.in);
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		System.out.println("Please enter the amount you want to withdraw");
		System.out.print("Amount: ");
		int withdraw = sc.nextInt();
		accDAO.withdraw(username, withdraw, accountNumber);
		
	}
	
	public static void sendMoney(String username, int accountNumber) {
		
		Scanner sc = new Scanner(System.in);
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		System.out.println("Please enter the amount you want to transfer the account number of the recipient");
		System.out.print("Amount: ");
		int transfer = sc.nextInt();
		System.out.print("Account Number: ");
		int accountNum = sc.nextInt();
		accDAO.postTransfer(username, accountNumber, transfer, accountNum);
		
	}
	
	public static void receiveMoney(int recAccountNumber) {
		
		Scanner sc = new Scanner(System.in);
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		Transfer t = new Transfer();
		
		try {
			t = accDAO.checkTransfer(recAccountNumber);
		} catch (TransferNotFoundException e) {
			e.printStackTrace();
			System.out.println("No pending transfers to approve.");
			return;
		} catch (InternalErrorException e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("You have amount waiting to be deposited.\n" + 
							t + "\n" +
							"Enter 1 if you want to approve the transfer");
		System.out.print("Your Choice: ");
		int choice = sc.nextInt();
		
		if (choice == 1) accDAO.receiveTransfer(t.getTransferAmount(), recAccountNumber);
		else System.out.println("Invalid input");
		
	}

}
