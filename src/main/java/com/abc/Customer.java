package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
	private String name;
	private List<Account> accounts;

	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public Customer openAccount(Account account) {
		accounts.add(account);
		return this;
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public double totalInterestEarned() {
		double total = 0;
		for (Account account : accounts)
			total += account.interestEarned();
		return total;
	}

	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		double total = 0.0;
		for (Account account : accounts) {
			statement += "\n" + statementForAccount(account) + "\n";
			total += account.sumTransactions();
		}
		statement += "\nTotal In All Accounts " + toDollars(total);
		return statement;
	}

	private String statementForAccount(Account account) {
		String statement = "";

		// Translate to pretty account type
		switch (account.getAccountType()) {
		case Account.CHECKING:
			statement += "Checking Account\n";
			break;
		case Account.SAVINGS:
			statement += "Savings Account\n";
			break;
		case Account.MAXI_SAVINGS:
			statement += "Maxi Savings Account\n";
			break;
		}

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction transaction : account.transactions) {
			statement += "  "
					+ (transaction.amount < 0 ? "withdrawal" : "deposit") + " "
					+ toDollars(transaction.amount) + "\n";
			total += transaction.amount;
		}
		statement += "Total " + toDollars(total);
		return statement;
	}

	private String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}

	/**
	 * New feature to transfer amount between accounts
	 * 
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 */
	public void trasnfer(Account fromAccount, Account toAccount, Double amount) {
		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
	}
}
