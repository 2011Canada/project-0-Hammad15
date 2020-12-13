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
		// TODO Auto-generated method stub
		return null;
	}



	public void makeNewCustomer(String firstName, String lastName, String username, String password) {
		
		Connection conn = cf.getConnection();
		try {
			String sql = "insert into customers (first_name, last_name, username, \"password\") values (?, ?, ?, ?)";
			
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
			if(res.next()) {
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
}
