package com.ets.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class TimeStampUtil {

	private static Logger logger = Logger.getLogger(TimeStampUtil.class);

	public static String getCurrentTimeStamp() {

		String date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(WebConstants.DATE_FORMAT);
			Calendar cal = Calendar.getInstance();
			date = dateFormat.format(cal.getTime());
		} catch (Exception e) {

			logger.error("Exception whyle getting date: " + e.getMessage());
		}

		return date;
	}
}
