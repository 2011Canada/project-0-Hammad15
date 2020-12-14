package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.models.Transaction;
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
			conn.setAutoCommit(false);
			
			String sql = "update applications set application_status = 'rejected' where username = ?;";
			PreparedStatement updateType = conn.prepareStatement(sql);
			updateType.setString(1, username);
			updateType.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
			}
		
		System.out.println("Application rejected!\nYou will be directed back to your home page");
		
	}

	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transaction> findAllTransactions() {
		// TODO Auto-generated method stub
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

	public void deposit(String username, int depositAmount) {
		// TODO Auto-generated method stub
		
	}

	public void withdraw(String username, int withdrawAmount) {
		// TODO Auto-generated method stub
		
	}

	public void postTransfer(String username, int amount, int accountNumber) {
		// TODO Auto-generated method stub
		
	}

}
