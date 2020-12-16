package com.revature.services;

import java.util.Scanner;

import com.revature.models.Application;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountPostgresDAO;

public class EmployeeServices {
	
	public static void viewPendingApplication() {
		
		Application app = new Application();
		AccountDAO accDAO = new AccountPostgresDAO();
		
		Scanner sc = new Scanner(System.in);
		
		if (accDAO.findApplicationTypes().contains("pending")) {
			app = accDAO.findPendingApplication();
			System.out.println(app);
			System.out.println("Enter 1 if you want to approve the application, enter 2 if you want to reject it");
			System.out.print("Your choice: ");
			int y = sc.nextInt();
			switch (y) {
			case 1:
				accDAO.approveBankAccount(app.getUsername(), app.getStartingBalance());
				break;
			case 2:
				accDAO.rejectBankAccount(app.getUsername());
				break;
			}
		} else {
			System.out.println("There are no pending applications");
		}
		
	}
	
	public void viewAllAccounts() {
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		System.out.println(accDAO.findAllAccounts());
		
	}
	
	public static void viewAccountByFirstNameAndLastName(String firstname, String lastname) {
		
		//TODO
		
	}
	
	public static void viewAllTransactions() {
		
		AccountDAO accDAO = new AccountPostgresDAO();
		
		System.out.println(accDAO.findAllTransactions());
		
	}

}
