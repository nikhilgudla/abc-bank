package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

	@Test
	// Test customer statement generation
	public void testApp() {

		Account checkingAccount = new Account(Account.CHECKING);
		Account savingsAccount = new Account(Account.SAVINGS);

		Customer henry = new Customer("Henry").openAccount(checkingAccount)
				.openAccount(savingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);

		assertEquals("Statement for Henry\n" + "\n" + "Checking Account\n"
				+ "  deposit $100.00\n" + "Total $100.00\n" + "\n"
				+ "Savings Account\n" + "  deposit $4,000.00\n"
				+ "  withdrawal $200.00\n" + "Total $3,800.00\n" + "\n"
				+ "Total In All Accounts $3,900.00", henry.getStatement());
	}

	@Test
	public void testOneAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				Account.SAVINGS));
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	@Test
	public void testTwoAccount() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	@Ignore
	public void testThreeAcounts() {
		Customer oscar = new Customer("Oscar").openAccount(new Account(
				Account.SAVINGS));
		oscar.openAccount(new Account(Account.CHECKING));
		assertEquals(3, oscar.getNumberOfAccounts());
	}

	@Test
	public void transfer() {
		Account savingsAccount = new Account(Account.SAVINGS);
		Account checkingAccount = new Account(Account.CHECKING);
		Customer nick = new Customer("Nick").openAccount(savingsAccount);
		nick.openAccount(checkingAccount);

		// Transfer between customer accounts
		Double amount = 100.00;
		checkingAccount.deposit(amount);
		nick.trasnfer(checkingAccount, savingsAccount, amount);

		// Assert
		assertEquals(checkingAccount.sumTransactions(), 0, 0);
		assertEquals(savingsAccount.sumTransactions(), 100.0, 0);

	}
}
