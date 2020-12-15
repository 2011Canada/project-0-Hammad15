package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Transaction;
import com.revature.models.Transfer;

public interface AccountDAO {
	
	public List<String> findApplicationTypes();
	
	public Application findPendingApplication();
	
	public void approveBankAccount(String username, int startingBalance);
	
	public void rejectBankAccount(String username);
	
	public List<Account> findAllAccounts();
	
	public List<Transaction> findAllTransactions();
	
	public Account viewAccount(String username) throws AccountNotFoundException, InternalErrorException;
	
	public void deposit(String username, int depositAmount, int accountNumber);
	
	public void withdraw(String username, int withdrawAmount, int accountNumber);
	
	public void postTransfer(String username, int accountNumber, int amount, int recAccountNumber);
	
	public Transfer checkTransfer(int recAccountNumber) throws TransferNotFoundException, InternalErrorException;
	
	public void receiveTransfer(int transferAmount, int recAccountNumber);

}
