package com.example.restful.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.restful.utils.DBConnection;
import com.example.restful.utils.Utility;

//Path: http://localhost/<appln-folder-name>/restful/register
@Path("/register")
public class CRegister {

    //Using chrome application Avanced rest client using post o get method
    //http://localhost:8080/restful-backend-users/restful/register/doregister?name=Admin&username=admin@programmerguru.com&password=password
    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/restful/register/doregister
    @Path("/doregister")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/restful/register/doregister?name=pqrs&username=abc&password=xyz
    public String doGetLogin( @QueryParam("name") String strName, @QueryParam("username") String strUserName, @QueryParam("password") String strPassword ){
    
        return doCommonLogin( strName, strUserName, strPassword );
 
    }

    // HTTP Post Method
    @POST
    // Path: http://localhost/<appln-folder-name>/restful/register/doregister
    @Path("/doregister")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/restful/register/doregister?name=pqrs&username=abc&password=xyz
    public String doPostLogin( @FormParam("name") String strName, @FormParam("username") String strUserName, @FormParam("password") String strPassword ){
    
        return doCommonLogin( strName, strUserName, strPassword );
 
    }
    
    public String doCommonLogin( String strName, String strUserName, String strPassword ){
        
        String strResponse = "";

        int registerUserCode = this.registerUser( strName, strUserName, strPassword );

        if ( registerUserCode == 0 ) {
        
            strResponse = Utility.constructJSON( "register", true );
            
        }
        else if ( registerUserCode == 1 ) {
            
            strResponse = Utility.constructJSON( "register", false, "You are already registered" );
            
        }
        else if ( registerUserCode == 2 ) {
            
            strResponse = Utility.constructJSON( "register", false, "Special Characters are not allowed in Username and Password" );
            
        }
        else if ( registerUserCode == 3 ) {
            
            strResponse = Utility.constructJSON( "register", false, "Error occured" );
            
        }
        
        return strResponse;
 
    }
    
    
    private int registerUser( String strName, String strUserName, String strPassword ){

        System.out.println( "Inside checkCredentials" );
        
        int intRegisterResult = 3;
        
        if ( Utility.isNotNull( strUserName ) && Utility.isNotNull( strPassword ) ) {
        
            intRegisterResult = DBConnection.insertUser( strName, strUserName, strPassword );
        
        }
 
        return intRegisterResult;
        
    }
 
}