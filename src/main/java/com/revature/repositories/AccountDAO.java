package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Transaction;

public interface AccountDAO {
	
	public List<String> findApplicationTypes();
	
	public Application findPendingApplication();
	
	public void approveBankAccount(String username, int startingBalance);
	
	public void rejectBankAccount(String username);
	
	public List<Account> findAllAccounts();
	
	public List<Transaction> findAllTransactions();
	
	public Account viewAccount(String username);
	
	public void deposit(String username, int depositAmount);
	
	public void withdraw(String username, int withdrawAmount);
	
	public void postTransfer(String username, int amount, int accountNumber);

}
