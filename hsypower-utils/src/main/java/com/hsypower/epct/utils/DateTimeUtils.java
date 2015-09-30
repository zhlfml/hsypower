package com.hsypower.epct.utils;

import java.util.Date;

import org.joda.time.DateTime;

public class DateTimeUtils {
	
	public static String format(Date date) {
		DateTime dt = new DateTime(date);
		
		return dt.toString("yyyy-MM-dd HH:mm:ss");
	}
	
}
