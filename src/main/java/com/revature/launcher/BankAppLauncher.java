package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.LoginMenu;

public class BankAppLauncher {
	
	public static Logger bankLogger = LogManager.getLogger("com.revature.bank");

	public static void main(String[] args) {
		
		bankLogger.info("Server has started");
		
		while (true) {
			
			LoginMenu.display();			
			
			}
		}
	}
