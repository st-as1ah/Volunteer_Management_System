/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;

//import java.sql.DataSource;

/**
 *
 * @author User
 */
public class DBConnection {
         
    // create a function to create & return the connection
    public static Connection createConnection() 
    {
        Connection conn=null;
        String url = "jdbc:derby://localhost:1527/VolunteerDB";
        String username = "APP";
        String password = "abc123";
        try{
            try{
            // Load the driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            }catch(ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            // Connect to sample database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Printing connection object"+conn);
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return conn;
    }
}
