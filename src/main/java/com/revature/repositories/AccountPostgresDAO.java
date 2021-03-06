package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Transaction;
import com.revature.models.Transfer;
import com.revature.util.ConnectionFactory;

public class AccountPostgresDAO implements AccountDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	public List<String> findApplicationTypes() {
		
		List<String> applicationTypes = new ArrayList<String>();
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select application_status from applications;";
			PreparedStatement selectAppTypes = conn.prepareStatement(sql);
			
			ResultSet res = selectAppTypes.executeQuery();
			while(res.next()) {
				String appType = res.getString("application_status");
				applicationTypes.add(appType);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			//throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
			}
		return applicationTypes;
	}
	
	public Application findPendingApplication() {
		
		Application a = new Application();
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from applications where application_status = 'pending';";
			PreparedStatement getApp = conn.prepareStatement(sql);
			
			ResultSet res = getApp.executeQuery();
			if (res.next()) {
				a.setApplication_id(res.getInt("application_id"));
				a.setUsername(res.getString("username"));
				a.setStartingBalance(res.getInt("starting_balance"));
				a.setCreditScore(res.getInt("credit_score"));
				a.setYearlySalary(res.getInt("yearly_salary"));
				a.setApplicationStatus(res.getString("application_status"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
		
		return a;
		
	}
	
	public void approveBankAccount(String username, int startingBalance) {
		
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql1 = "insert into accounts (username, account_balance) values (?, ?);";
			PreparedStatement makeAccount = conn.prepareStatement(sql1);
			makeAccount.setString(1, username);
			makeAccount.setInt(2, startingBalance);
			makeAccount.executeUpdate();
			
			String sql2 = "update applications set application_status = 'approved' where username = ?;";
			PreparedStatement updateType = conn.prepareStatement(sql2);
			updateType.setString(1, username);
			updateType.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
			}
		
		System.out.println("Application approved and new bank account created!\nYou will be directed back to your home page");
		
	}

	public void rejectBankAccount(String username) {
		
		Connection conn = cf.getConnection();
		try {
			
			String sql = "update applications set application_status = 'rejected' where username = ?;";
			PreparedStatement updateType = conn.prepareStatement(sql);
			updateType.setString(1, username);
			updateType.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
			}
		
		System.out.println("Application rejected!\nYou will be directed back to your home page");
		
	}

	public List<Account> findAllAccounts() {
		
		Connection conn = cf.getConnection();
		try {
			List<Account> accounts = new ArrayList<Account>();
			String sql = "select * from accounts;";
			PreparedStatement getAccounts = conn.prepareStatement(sql);
			
			ResultSet res = getAccounts.executeQuery();

			while(res.next()) {
				Account a = new Account();
				a.setAccountNumber(res.getInt("account_number"));
				a.setUsername(res.getString("username"));
				a.setAccountBalance(res.getInt("account_balance"));
				accounts.add(a);
			}
			return accounts;
		}catch(SQLException e) {
			e.printStackTrace();
//			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
		
		return null;

	}

	public List<Transaction> findAllTransactions() {
		
		Connection conn = cf.getConnection();
		try {
			List<Transaction> transactions = new ArrayList<Transaction>();
			String sql = "select * from transactions;";
			PreparedStatement getTransactions = conn.prepareStatement(sql);
			
			ResultSet res = getTransactions.executeQuery();

			while(res.next()) {
				Transaction t = new Transaction();
				t.setTransaction_id(res.getInt("transaction_id"));
				t.setAccountNumber(res.getInt("account_number"));
				t.setTransactionType(res.getString("t_type"));
				t.setTransactionAmount(res.getInt("t_amount"));
				transactions.add(t);
			}
			return transactions;
		}catch(SQLException e) {
			e.printStackTrace();
//			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
		
		return null;
	}

	public Account viewAccount(String username) throws AccountNotFoundException, InternalErrorException {
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from accounts where username = ?;";
			PreparedStatement getAccount = conn.prepareStatement(sql);
			getAccount.setString(1, username);
			
			ResultSet res = getAccount.executeQuery();
			if(res.next()) {
				Account a = new Account();
				a.setAccountNumber(res.getInt("account_number"));
				a.setUsername(res.getString("username"));
				a.setAccountBalance(res.getInt("account_balance"));
				return a;
			}else {
				throw new AccountNotFoundException();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}

	public void deposit(String username, int depositAmount, int accountNumber) {
		
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql1 = "update accounts set account_balance = account_balance + ? where username = ?;";
			PreparedStatement deposit = conn.prepareStatement(sql1);
			deposit.setInt(1, depositAmount);
			deposit.setString(2, username);
			deposit.executeUpdate();
			
			String sql2 = "insert into transactions (account_number, t_type, t_amount) values (?, ?, ?);";
			String tType = "deposit";
			PreparedStatement insertTransaction = conn.prepareStatement(sql2);
			insertTransaction.setInt(1, accountNumber);
			insertTransaction.setString(2, tType);
			insertTransaction.setInt(3, depositAmount);
			insertTransaction.executeUpdate();
			
			conn.commit();
			
			System.out.println("Congratulations! $" + depositAmount + " has been added to your account.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
			}	
	}

	public void withdraw(String username, int withdrawAmount, int accountNumber) {
		
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql1 = "update accounts set account_balance = account_balance - ? where username = ?;";
			PreparedStatement withdraw = conn.prepareStatement(sql1);
			withdraw.setInt(1, withdrawAmount);
			withdraw.setString(2, username);
			withdraw.executeUpdate();
			
			String sql2 = "insert into transactions (account_number, t_type, t_amount) values (?, ?, ?);";
			String tType = "withdraw";
			PreparedStatement insertTransaction = conn.prepareStatement(sql2);
			insertTransaction.setInt(1, accountNumber);
			insertTransaction.setString(2, tType);
			insertTransaction.setInt(3, withdrawAmount);
			insertTransaction.executeUpdate();
			
			conn.commit();
			
			System.out.println("Congratulations! You have withdrawn $" + withdrawAmount + " from your account.");
			
		}catch(SQLException e) {
			System.out.println("Transaction failed! Account will go in overdraft.");
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
			}		
	}

	public void postTransfer(String username, int accountNumber, int amount, int recAccountNumber) {

		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql1 = "update accounts set account_balance = account_balance - ? where username = ?;";
			PreparedStatement send = conn.prepareStatement(sql1);
			send.setInt(1, amount);
			send.setString(2, username);
			send.executeUpdate();
			
			String sql2 = "insert into transactions (account_number, t_type, t_amount) values (?, ?, ?);";
			String tType = "transfer out";
			PreparedStatement insertTransaction = conn.prepareStatement(sql2);
			insertTransaction.setInt(1, accountNumber);
			insertTransaction.setString(2, tType);
			insertTransaction.setInt(3, amount);
			insertTransaction.executeUpdate();
			
			String sql3 = "insert into transfers (from_account_number, amount, to_account_number) values (?, ?, ?);";
			PreparedStatement insertTransfer = conn.prepareStatement(sql3);
			insertTransfer.setInt(1, accountNumber);
			insertTransfer.setInt(2, amount);
			insertTransfer.setInt(3, recAccountNumber);
			insertTransfer.executeUpdate();
			
			conn.commit();
			
			System.out.println("Congratulations! You have transferred $" + amount + " to the bank account, " + recAccountNumber + ".");
			
		}catch(SQLException e) {
			System.out.println("Transaction failed! Account will go in overdraft.");
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
			}
	}

	public Transfer checkTransfer(int recAccountNumber) throws TransferNotFoundException, InternalErrorException {

		Connection conn = cf.getConnection();
		try {
			String sql = "select * from transfers where to_account_number = ?;";
			PreparedStatement getTransfer = conn.prepareStatement(sql);
			getTransfer.setInt(1, recAccountNumber);
			
			ResultSet res = getTransfer.executeQuery();
			if(res.next()) {
				Transfer t = new Transfer();
				t.setAccountNumber(res.getInt("from_account_number"));
				t.setTransferAmount(res.getInt("amount"));
				t.setRecAccountNumber(res.getInt("to_account_number"));
				t.setTransferStatus(res.getString("status"));
				return t;
			}else {
				throw new TransferNotFoundException();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
		
	}
	
	public void receiveTransfer(int transferAmount, int recAccountNumber) {
		
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql1 = "update accounts set account_balance = account_balance + ? where account_number = ?;";
			PreparedStatement receive = conn.prepareStatement(sql1);
			receive.setInt(1, transferAmount);
			receive.setInt(2, recAccountNumber);
			receive.executeUpdate();
			
			String sql2 = "insert into transactions (account_number, t_type, t_amount) values (?, ?, ?);";
			String tType = "transfer in";
			PreparedStatement insertTransaction = conn.prepareStatement(sql2);
			insertTransaction.setInt(1, recAccountNumber);
			insertTransaction.setString(2, tType);
			insertTransaction.setInt(3, transferAmount);
			insertTransaction.executeUpdate();
			
			String sql3 = "update transfers set status = 'accepted' where to_account_number = ?;";
			PreparedStatement insertTransfer = conn.prepareStatement(sql3);
			insertTransfer.setInt(1, recAccountNumber);
			insertTransfer.executeUpdate();
			
			conn.commit();
			
			System.out.println("Congratulations! You have accepted a transfer of $" + transferAmount);
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
			}
	}

	

}
