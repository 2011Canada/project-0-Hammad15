package com.revature.launcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.menus.CustomerLogin;
import com.revature.menus.CustomerRegister;
import com.revature.menus.EmployeeLogin;
import com.revature.menus.LoginMenu;
import com.revature.models.Customer;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.util.ConnectionFactory;

public class BankAppLauncher {

	public static void main(String[] args) {
		
//		Connection conn = null;
//		try {
//			String url = System.getenv("DB_URL");
//			String user = System.getenv("DB_USER");
//			String password = System.getenv("DB_PASSWORD");
//			System.out.println(password);
//			conn = DriverManager.getConnection(url, user, password);
//			
//			System.out.println("Got it!");
//		} catch (SQLException e) {
//			throw new Error("problem", e);
//		} finally {
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException ex) {
//				System.out.println(ex.getMessage());
//			}
//		}
		
		Scanner sc = new Scanner(System.in);
		
		Customer cust = new Customer();
		
		CustomerDAO custDAO = new CustomerPostgresDAO();
		
		while (true) {
			
			
			
			System.out.println("\nWelcome to Hammad's Bank \n\n" 
					+ "Please enter the appropriate number to continue \n"
					+ "1. Login as an employee \n"
					+ "2. Login as a Customer \n"
					+ "3. Register as a Customer");
			
			System.out.print("Your choice: ");
			
			int n = sc.nextInt();
			
			if (n == 1) {
				System.out.println(EmployeeLogin.display());
				System.out.print("Username: ");
				String username = sc.next();
				System.out.print("Password: ");
				String password = sc.next();
				
			} else if (n == 2) {
				System.out.println(CustomerLogin.display());
				System.out.print("Username: ");
				String username = sc.next();
				System.out.print("Password: ");
				String password = sc.next();
				
				try {
					cust = custDAO.findCustomerByUsernameAndPassword(username, password);
				} catch (UserNotFoundException e) {
					e.getMessage();
					System.out.println("User Not Found\nYou will be returned to the main menu.\n\n");
				} catch (InternalErrorException e) {
					e.getMessage();
					System.out.println("OOPS, something went wrong.\n\n");
				}
				
				System.out.println(cust);
				
			} else if (n == 3) {
				System.out.println(CustomerRegister.display());
				System.out.print("First Name: ");
				String firstName = sc.next();
				System.out.print("Last Name: ");
				String lastName = sc.next();
				System.out.print("Username: ");
				String username = sc.next();
				if (custDAO.getAllUsernames().contains(username)) {
					System.out.println("The entered username is already in use, please choose a different one. " +
										"You will now be going back to the main menu");
					break;
				}
				System.out.print("Password: ");
				String password = sc.next();
				
				custDAO.makeNewCustomer(firstName, lastName, username, password);
				
			}
			
			//sc.close();
			
//			System.out.println(LoginMenu.display());
//			
//			Scanner sc = new Scanner(System.in);
//			
//			int n = sc.nextInt();
//			
//			switch (n) {
//			
//			case 1 :
//				System.out.println(EmployeeLogin.display());
//				
//				break;
//			case 2 :
//				System.out.println(CustomerLogin.display());
//				break;
//			case 3 :
//				System.out.println(CustomerRegister.display());
//				break;
//			}
			
			}
			
		
			
		}
	}
