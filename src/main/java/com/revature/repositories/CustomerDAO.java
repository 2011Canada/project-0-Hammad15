package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;

public interface CustomerDAO {
	
	public Customer findCustomerByUsernameAndPassword(String username, String password) throws UserNotFoundException, InternalErrorException;
	
	public void makeNewCustomer(String firstName, String lastName, String username, String password);
	
	public List<Customer> findAll();
	
	public List<String> getAllUsernames();

}
