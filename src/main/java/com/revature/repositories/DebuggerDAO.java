package com.revature.repositories;

import java.util.Scanner;

import com.revature.models.Application;
import com.revature.launcher.BankAppLauncher;

public class DebuggerDAO {

	public static void main(String[] args) {
		
		CustomerPostgresDAO cDAO = new CustomerPostgresDAO();
		
//		Application app = cDAO.accountApplication("Hammad", 10000, 750, 85000);
		
//		System.out.println(app);
		
		System.out.println(cDAO.findAll());
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Yearly Salary: ");
		double yearly_salary = sc.nextDouble();
		
	}

}
