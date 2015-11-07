package com.example.restful.utils;

import org.codehaus.jettison.json.JSONObject;

public class Utility {

    public static boolean isNotNull( String strTextToCheck ) {
     
        return strTextToCheck != null && strTextToCheck.trim().length() >= 0 ? true : false;
        
    }    

    public static String constructJSON( String strTag, boolean bStatus ) {
        
        JSONObject obj = new JSONObject();
        
        try {
            
            obj.put( "tag", strTag );
            obj.put( "status", new Boolean(bStatus) );
            
        } 
        catch ( Exception e ) {
      
            e.printStackTrace(); 
        
        }
        
        return obj.toString();
        
    }
    
    public static String constructJSON( String strTag, boolean bStatus, String strErrorMessage ) {
     
        JSONObject obj = new JSONObject();
        
        try {
        
            obj.put( "tag", strTag );
            obj.put( "status", new Boolean(bStatus) );
            obj.put( "error_msg", strErrorMessage );
            
        } 
        catch ( Exception e ) {

            e.printStackTrace();
        
        }
        
        return obj.toString();
        
    }   
    
}
