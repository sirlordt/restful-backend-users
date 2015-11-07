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

import org.codehaus.jettison.json.JSONObject;

//The /restful/  provided in web.xml servlet-mapping

//Path: http://localhost/<appln-folder-name>/restful/onlyauthenticated
@Path("/onlyauthenticated")
public class COnlyAuthenticated {

    @Context 
    HttpServletRequest ServletRequest;
    
    //Using chrome application Avanced rest client using post o get method
    //http://localhost:8080/restful-backend-users/restful/onlyauthenticated/domessage?message=nice!!!!
    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/restful/onlyauthenticated/domessage
    @Path( "/domessage" )
    // Produces JSON as response
    @Produces( MediaType.APPLICATION_JSON ) 
    // Query parameters are parameters: http://localhost:8080/<appln-folder-name>/restful/onlyauthenticated/domessage?message=nice!!!!
    public String doGetMessage( @QueryParam("message") String strMessage ){
        
        return this.doCommonMessage( strMessage );
        
    }

    // HTTP Post Method
    @POST
    // Path: http://localhost/<appln-folder-name>/restful/onlyauthenticated/domessage
    @Path( "/domessage" )
    // Produces JSON as response
    @Produces( MediaType.APPLICATION_JSON ) 
    // Query parameters are parameters: http://localhost:8080/<appln-folder-name>/restful/onlyauthenticated/domessage?message=nice!!!!
    public String doPostMessage( @FormParam("message") String strMessage ){

        return this.doCommonMessage( strMessage );
        
    }

    public String doCommonMessage( String strMessage ){

        String response = "";

        HttpSession session = ServletRequest.getSession(true);
        
        try {

            JSONObject obj = new JSONObject();
            
            if ( session.getAttribute( "logged" ) != null ){

                obj.put( "tag", "logged" );
                obj.put( "status", true );
                obj.put( "msg", strMessage );

            }
            else{

                obj.put( "tag", "logged" );
                obj.put( "status", false );
                obj.put( "msg", "your must logged for access this service!" );

            }
        
            response = obj.toString();
            
        } 
        catch ( Exception e ) {

            e.printStackTrace();
        
        }
        
        return response;
        
    }
    
}
