package org.comit.course.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//THIS CLASS CREATED FOR DATE TRANSFORMATION or converting date from string to a date
//this class has only parameters
//

public class Util { 
	
	public static Date parseDate(String str) { //static helps us call method directly without creating the object 
										// public create this method visibly
		
		Date date = null; //created date variable
		
		
		
		//took code from google Simple date format (parsing dates)
				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(str.trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static int parseId(String id) {
		return (id==null)?0:Integer.parseInt(id.trim());
	}
	


}
