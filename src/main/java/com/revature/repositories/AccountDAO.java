package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Transaction;

public interface AccountDAO {
	
	public List<Account> findAllAccounts();
	
	public Application findPendingApplication ();
	
	public List<Transaction> findAllTransactions();
	
	public Account approveBankAccount(String username);

}
