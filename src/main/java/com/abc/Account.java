package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	private final int accountType;
	public List<Transaction> transactions;

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

	public double interestEarned() {
		double amount = sumTransactions();

		if (amount < 0)
			return 0;

		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;
		case MAXI_SAVINGS:
			if (this.noWithdrawalsInPastTenDays()) {
				return amount * .05;
			}
			return amount * .01;
		case CHECKING:
			return amount * 0.001;
		default:
			return 0;
		}
	}

	/**
	 * Interest is accrued daily as per the new feature including weekends
	 * 
	 * @return interest
	 */
	public double interestEarnedPerDay() {
		double accrueDayCount = 365;
		double amount = sumTransactions();

		// If amount is 0, interest is defaulted to 0.
		if (amount < 0)
			return 0;

		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001 / accrueDayCount;
			else
				return 1 + (amount - 1000) * 0.002 / accrueDayCount;
		case MAXI_SAVINGS:
			if (this.noWithdrawalsInPastTenDays()) {
				return amount * .05 / accrueDayCount;
			}
			return amount * .01 / accrueDayCount;
		case CHECKING:
			return amount * 0.001 / accrueDayCount;
		default:
			return 0;
		}
	}

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction transaction : transactions)
			amount += transaction.amount;
		return amount;
	}

	public int getAccountType() {
		return accountType;
	}

	public boolean noWithdrawalsInPastTenDays() {
		for (Transaction t : transactions)
			if (t.occuredInPastTenDays())
				return false;
		return true;
	}
}
