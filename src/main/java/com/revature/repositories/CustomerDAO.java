package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Application;
import com.revature.models.Customer;
import com.revature.models.Employee;

public interface CustomerDAO {
	
	public Customer findCustomerByUsernameAndPassword(String username, String password) throws UserNotFoundException, InternalErrorException;
	
	public void makeNewCustomer(String firstName, String lastName, String username, String password);
	
	public List<Customer> findAll();
	
	public List<String> getAllUsernames();
	
	public Application accountApplication(String username, int startingBalance, int creditScore, int yearlySalary);

	public Employee findEmployeeByUsernameAndPassword(String username, String password) throws UserNotFoundException, InternalErrorException;

}
