package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * Provides date instance or current date
 * 
 * @author Nikhil
 *
 */
public class DateProvider {
	private static DateProvider instance = null;

	/**
	 * This method provides new DateProvider instance
	 * 
	 * @return DateProvider
	 */
	public static DateProvider getInstance() {
		if (instance == null)
			instance = new DateProvider();
		return instance;
	}

	/**
	 * This method provides the date now
	 * 
	 * @return Date
	 */
	public Date now() {
		return Calendar.getInstance().getTime();
	}
}
