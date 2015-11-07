package com.example.restful.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    public static Connection createConnection() {

        Connection dbConnection = null;

        try {

            Class.forName( DBConstants.dbClass );

            dbConnection = DriverManager.getConnection( DBConstants.dbUrl, DBConstants.dbUser, DBConstants.dbPwd );

        } 
        catch ( Exception e ) {

            e.printStackTrace();

        } 

        return dbConnection;

    }

    public static boolean checkLogin( String uname, String pwd ) {

        boolean bIsUserAvailable = false;

        Connection dbConnection = null;

        try {

            dbConnection = DBConnection.createConnection();

            Statement stmt = dbConnection.createStatement();

            String query = "SELECT * FROM user WHERE username = '" + uname + "' AND password=" + "'" + pwd + "'";

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {

                //System.out.println( rs.getString( "username" ) + rs.getString( "password" ) + rs.getString( "name" ) );
                bIsUserAvailable = true;

            }

        } 
        catch ( Exception e ) {

            e.printStackTrace();

        }

        if ( dbConnection != null ) {

            try {

                dbConnection.close();

            } 
            catch (Exception e) {

                e.printStackTrace();

            }
            

        }

        return bIsUserAvailable;

    }	

    public static int insertUser( String strName, String strUserName, String strPassword ) {
        
        int intInsertResult = 3;
        
        Connection dbConnection = null;
        
        try {
            
            dbConnection = DBConnection.createConnection();
            
            Statement stmt = dbConnection.createStatement();
            
            String strQuery = "INSERT into user(name, username, password) values('" + strName+ "'," + "'" + strUserName + "','" + strPassword + "')";
            //System.out.println(query);

            if ( stmt.executeUpdate( strQuery ) > 0 )
                intInsertResult = 0;
        
        } 
        catch( SQLException sqlEx ){
            
            System.out.println( sqlEx.getErrorCode() );
            
            //When Primary key violation occurs that means user is already registered
            if( sqlEx.getErrorCode() == 1062 ) {
                
                intInsertResult = 1;
                
            } 
            //When special characters are used in name, username or password
            else if ( sqlEx.getErrorCode() == 1064 ) {
                
                intInsertResult = 2;
                
            }
            
        }        
        catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }

        if ( dbConnection != null ) {

            try {

                dbConnection.close();

            } 
            catch ( Exception ex ) {

                ex.printStackTrace();

            }
            

        }
        
        return intInsertResult;
        
    }
    
}
