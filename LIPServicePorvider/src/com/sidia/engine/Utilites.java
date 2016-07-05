package com.sidia.engine;

import java.lang.reflect.Field;

public class Utilites {




/*
 * @param Object Request need to parsed //Request object need to be parsed
 * Brief return the string request based on parameters inside the Class
 * */


public static String getGoogleRouteRequest(Object request) 
{
	try{
	StringBuilder requestBuilder=new StringBuilder();
	if(request==null)
		throw new NullPointerException();
	Class<? extends Object> requestClass=request.getClass();
	  Field[] valueObjFields = requestClass.getDeclaredFields();

	  // compare values now
	  for (int i = 0; i< valueObjFields.length; i++)
	  {
	     String fieldName = valueObjFields[i].getName();
	     valueObjFields[i].setAccessible(true);
	     Object newObj = valueObjFields[i].get(request);
	     
	     if(fieldName!=null&&newObj!=null)
	     {
	    	 requestBuilder.append(fieldName+"="+newObj.toString());
	    	 if(i!=valueObjFields.length-1)
	    	 {
	    		 requestBuilder.append("&");
	    	 }
	     }
	     
	  }
	
	  return requestBuilder.toString();
	}catch(Exception ee)
	{
		ee.printStackTrace();
		return null;
	}
}

}
