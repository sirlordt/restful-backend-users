package com.example.restful.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

//The /restful/  provided in web.xml servlet-mapping

//Path: http://localhost/<appln-folder-name>/restful/onlyauthenticated
@Path("/logout")
public class CLogout {

    @Context 
    HttpServletRequest ServletRequest;
    
    //Using chrome application Avanced rest client using post o get method
    //http://localhost:8080/restful-backend-users/restful/onlyauthenticated/domessage?message=nice!!!!
    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/restful/onlyauthenticated/domessage
    @Path( "/dologout" )
    // Produces JSON as response
    @Produces( MediaType.APPLICATION_JSON ) 
    // Query parameters are parameters: http://localhost:8080/<appln-folder-name>/restful/logout/dologout
    public String doGetLogout(){
        
        return this.doCommonLogout();
        
    }

    // HTTP Post Method
    @POST
    // Path: http://localhost/<appln-folder-name>/restful/logout/dologout
    @Path( "/dologout" )
    // Produces JSON as response
    @Produces( MediaType.APPLICATION_JSON ) 
    // Query parameters are parameters: http://localhost:8080/<appln-folder-name>/restful/logout/dologout
    public String doPostLogout(){

        return this.doCommonLogout();
        
    }

    public String doCommonLogout(){

        String response = "";

        HttpSession session = ServletRequest.getSession( false );
        
        try {

            
            JSONObject obj = new JSONObject();
            
            if ( session != null && session.getAttribute( "logged" ) != null ) {
                
                session.invalidate();
                
                obj.put( "tag", "logout" );
                obj.put( "status", true );
                obj.put( "msg", "logout sucessfull" );
                
            }
            else {
                
                obj.put( "tag", "logout" );
                obj.put( "status", false );
                obj.put( "msg", "logout failed" );
                
            }
        
            response = obj.toString();
            
        } 
        catch ( Exception e ) {

            e.printStackTrace();
        
        }
        
        return response;
        
    }
    
}
