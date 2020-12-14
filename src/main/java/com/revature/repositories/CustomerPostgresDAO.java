package com.revature.repositories;

import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Application;
import com.revature.models.Customer;



public class CustomerPostgresDAO implements CustomerDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	public Customer findCustomerByUsernameAndPassword(String username, String password) throws UserNotFoundException, InternalErrorException {
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from customers where username = ? and \"password\" = ? ;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			getUser.setString(1, username);
			getUser.setString(2, password);
			
			ResultSet res = getUser.executeQuery();
			if(res.next()) {
				Customer u = new Customer();
				u.setCustomer_id(res.getInt("customer_id"));
				u.setFirstName(res.getString("first_name"));
				u.setLastName(res.getString("last_name"));
				u.setUsername(res.getString("username"));
				u.setPassword(res.getString("password"));
				return u;
			}else {
				throw new UserNotFoundException();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	

	public List<Customer> findAll() {
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from customers;";
			PreparedStatement getUsers = conn.prepareStatement(sql);
			
			ResultSet res = getUsers.executeQuery();
			List<Customer> customers = new ArrayList<Customer>();
			while(res.next()) {
				Customer c = new Customer();
				c.setCustomer_id(res.getInt("customer_id"));
				c.setFirstName(res.getString("first_name"));
				c.setLastName(res.getString("last_name"));
				c.setUsername(res.getString("username"));
				c.setPassword(res.getString("password"));
				customers.add(c);
			}
			return customers;
		}catch(SQLException e) {
			e.printStackTrace();
//			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
		
		return null;
	}



	public void makeNewCustomer(String firstName, String lastName, String username, String password) {
		
		Connection conn = cf.getConnection();
		try {
			String sql = "insert into customers (first_name, last_name, username, \"password\") values (?, ?, ?, ?);";
			
//			String sql = "insert into customers (first_name, last_name, username, \"password\") values (?, ?, ?, ?)" +
//					" returning customer_id, first_name, last_name, username, \"password\";";
			
			PreparedStatement makeCustomer = conn.prepareStatement(sql);
			makeCustomer.setString(1, firstName);
			makeCustomer.setString(2, lastName);
			makeCustomer.setString(3, username);
			makeCustomer.setString(4, password);
			
			makeCustomer.executeUpdate();
			
//			ResultSet res = makeCustomer.executeQuery();
//			if(res.next()) {
//				Customer u = new Customer();
//				u.setCustomer_id(res.getInt("user_id"));
//				u.setFirstName(res.getString("first_name"));
//				u.setLastName(res.getString("last_name"));
//				u.setUsername(res.getString("username"));
//				u.setPassword(res.getString("password"));
//			return u;
//			}
			
		}catch(SQLException e) {
			e.printStackTrace();
//			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
			}
		
		System.out.println("New customer account created, congratulations!\nPlease login from the main menu.");
		
	}



	public List<String> getAllUsernames() {
		
		List<String> usernames = new ArrayList<String>();
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select username from customers;";
			PreparedStatement selectUsernames = conn.prepareStatement(sql);
			
			ResultSet res = selectUsernames.executeQuery();
			while(res.next()) {
				String username = res.getString("username");
				usernames.add(username);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			//throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
			}
		return usernames;

	}



	public Application accountApplication(String username, int startingBalance, int creditScore, int yearlySalary) {
		Connection conn = cf.getConnection();
		try {
			String sql = "insert into applications (username, starting_balance, credit_score, yearly_salary) values (?, ?, ?, ?)"
							+ "returning application_id, username, starting_balance, credit_score, yearly_salary;";
			PreparedStatement submitApplication = conn.prepareStatement(sql);
			submitApplication.setString(1, username);
			submitApplication.setInt(2, startingBalance);
			submitApplication.setInt(3, creditScore);
			submitApplication.setInt(4, yearlySalary);
			
			ResultSet res = submitApplication.executeQuery();
			if(res.next()) {
				Application app = new Application();
				app.setApplication_id(res.getInt("application_id"));
				app.setStartingBalance(res.getInt("starting_balance"));
				app.setCreditScore(res.getInt("credit_score"));
				app.setYearlySalary(res.getInt("yearly_salary"));
				return app;
			}
			
			System.out.println("You application has been submitted. Please check back in later to see if it was approved");
			
		}catch(SQLException e) {
			e.printStackTrace();
			//throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
		return null;
	}
}
