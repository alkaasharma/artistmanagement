package com.lambton.org.artistmanagement.util;

public class tyt {

	
	 static void check(Object userFromDB)
	{
		
		if(userFromDB == null){
            System.out.println("null");
          return;
        }
		 if(userFromDB=="jckjdk")
        {
        	   System.out.println("match");
        	   return;
        } 
        else
        {
        	 System.out.println("no match");
        	 return;
        }
	}

	
	public static void main(String[] args) {
		
		check(null);
	}
}
