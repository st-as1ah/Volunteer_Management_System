/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vol.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Asiah
 */
public class DBConnection {
    public static Connection createConnection() {
        
        // Declare driver and connection string
        Connection con = null;
        String url = "jdbc:derby://localhost:1527/VolunteerDB;create=true";
        String username = "APP";
        String password = "abc123";
        
        try
        {
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            }
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            con = DriverManager.getConnection(url, username, password);
            System.out.println("Printing connection object " + con);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
}
