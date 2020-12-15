package com.revature.launcher;

import com.revature.menus.LoginMenu;

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
		
		while (true) {
			
			LoginMenu.display();			
			
			}
		}
	}
