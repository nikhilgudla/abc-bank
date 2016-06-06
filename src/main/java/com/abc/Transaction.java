package com.abc;

import java.util.Date;

/**
 * 
 * This is Transaction Object, Constructor takes in transaction amount as
 * parameter
 * 
 * @author Nikhil
 *
 */
public class Transaction {
	public final double amount;

	private Date transactionDate;

	public Transaction(double amount) {
		this.amount = amount;
		this.transactionDate = DateProvider.getInstance().now();
	}

	public boolean occuredInPastTenDays() {
		Date now = new Date();
		// 10 Days of milliseconds 1000 * 60 * 60 *24 * 10 = 864000000
		if (transactionDate.getTime() >= now.getTime() - 864000000) {
			return true;
		}
		return false;
	}
}
