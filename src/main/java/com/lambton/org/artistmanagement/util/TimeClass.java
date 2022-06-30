package com.lambton.org.artistmanagement.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeClass {

	public static void main(String[] args) {
		Date date= new Date();
		System.out.println("util date "+date);//current date
		System.out.println("time in ms "+date.getTime());//time in milisec
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		System.out.println("sql date "+sqlDate);//only date
		java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
		System.out.println("sql timestamp "+sqlTime);
		
		Date utilDate = new Date(sqlDate.getTime());
		System.out.println(utilDate);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String formatted_date = dateFormat.format(utilDate);
        System.out.println(formatted_date);
		
	}
}
