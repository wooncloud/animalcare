package com.pet.care.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDate {

	public boolean CalSunday(String date) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd") ;
		Date nDate = dateFormat.parse(date);         
		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);         
		int day = cal.get(Calendar.DAY_OF_WEEK); 
		  
		if(day ==1) {
			return true; 
		}
		return false;
	}
}
