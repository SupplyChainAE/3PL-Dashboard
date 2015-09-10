package com.snapdeal.util;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class DateConvertor {
	
	public static final Logger LOGGER = Logger.getLogger(DateConvertor.class);
	
	public static java.sql.Date convertToDate(String dateString) 
	{
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new Date();
			
			date=df.parse(dateString);
			
			return new java.sql.Date(date.getTime());
		}
		catch(Exception ex)
		{
			LOGGER.error("Error in converting String to Date",ex);
		}
		return null;
	}
	
	public static String convertToString(Date date) 
	{
		try
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			String dateString;		
			dateString=df.format(date);
			
			return dateString;
		}
		catch(Exception ex)
		{
			LOGGER.error("Error in converting String to Date",ex);
		}
		return null;
	}

	public static String getDayOfMonth(Timestamp datetime)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String ts;		
		ts=df.format(datetime);
		String[] arr =ts.split("-");
		System.out.println(ts);
		return arr[0];
	}
}
