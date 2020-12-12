package com.revature.repositories;

import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;



public class CustomerPostgresDAO implements CustomerDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	public Customer findCustomerByUsernameAndPassword(String username, String password) throws UserNotFoundException, InternalErrorException {
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from customers where \"username\" = ? and \"password\" = ? ;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			getUser.setString(1, username);
			getUser.setString(2, password);
			
			ResultSet res = getUser.executeQuery();
			if(res.next()) {
				Customer u = new Customer();
				u.setCustomer_id(res.getInt("user_id"));
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

}
