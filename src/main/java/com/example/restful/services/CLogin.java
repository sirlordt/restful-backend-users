package com.example.restful.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.example.restful.utils.DBConnection;
import com.example.restful.utils.Utility;

//Path: http://localhost/<appln-folder-name>/restful/login
@Path("/login")
public class CLogin {

    @Context 
    HttpServletRequest ServletRequest;
    
    //Using chrome application Avanced rest client using post o get method
    //http://localhost:8080/restful-backend-users/restful/login/dologin?username=admin@programmerguru.com&password=password
    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/restful/login/dologin
    @Path( "/dologin" )
    // Produces JSON as response
    @Produces( MediaType.APPLICATION_JSON ) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/restful/login/dologin?username=abc&password=xyz
    public String doGetLogin( @QueryParam("username") String strUserName, @QueryParam("password") String strPassword ){
        
        return this.doCommonLogin( strUserName, strPassword );
        
    }

    // HTTP Post Method
    @POST
    // Path: http://localhost/<appln-folder-name>/restful/login/dologin
    @Path( "/dologin" )
    // Produces JSON as response
    @Produces( MediaType.APPLICATION_JSON ) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/restful/login/dologin
    //Form param
    //username=abc
    //password=xyz
    public String doPostLogin( @FormParam("username") String strUserName, @FormParam("password") String strPassword ){

        return this.doCommonLogin( strUserName, strPassword );
        
    }

    public String doCommonLogin( String strUserName, String strPassword ){

        String response = "";

        if ( this.checkCredentials( strUserName, strPassword ) ){
            
            HttpSession session = ServletRequest.getSession(true);
            
            session.setAttribute( "logged", true );
            
            response = Utility.constructJSON( "login",true );
            
        }
        else{
        
            response = Utility.constructJSON( "login", false, "Incorrect Email or Password" );
            
        }
    
        return response;
        
    }
    
    /**
     * Method to check whether the entered credential is valid
     * 
     * @param strUserName
     * @param strPassword
     * @return
     */
    private boolean checkCredentials( String strUserName, String strPassword ){

        //System.out.println( "Inside checkCredentials" );
        
        boolean bResult = false;

        if ( Utility.isNotNull( strUserName ) && Utility.isNotNull( strPassword ) ){

            bResult = DBConnection.checkLogin(strUserName, strPassword);
            
        }
 
        return bResult;
        
    }
 
}