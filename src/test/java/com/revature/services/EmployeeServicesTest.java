package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.revature.models.Account;
import com.revature.repositories.AccountDAO;

class EmployeeServicesTest {
	
	private EmployeeServices es;
	
	private AccountDAO accDAO;

	@BeforeEach
	public void setUpBeforeClass() throws Exception {
		this.accDAO = mock(AccountDAO.class);
		es = new EmployeeServices();
	}

	@Test
	public void testViewAllAccounts() {
		
		List<Account> testAccs = new ArrayList<Account>();
		Account henry = new Account(100001, "henry", 40000);
		Account domanic = new Account(100002, "domanic", 50000);
		testAccs.add(henry);
		testAccs.add(domanic);
		
		when(accDAO.findAllAccounts()).thenReturn(testAccs);
		
		List<Account> expectedValue = new ArrayList<Account>(testAccs);
		
		assertEquals(expectedValue, accDAO.findAllAccounts());
		
		
		//verify(accDAO).findAllAccounts();
		
	}

}
