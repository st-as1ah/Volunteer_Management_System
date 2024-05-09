/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Volunteer.dao;

//import com.vol.model.LoginVolunteer;
import Volunteer.bean.Volunteer;
import Volunteer.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Asiah
 */
public class VolunteerDAO {
    
    //Login Volunteer
    public String authenticateUser (Volunteer loginVol){
        
        String userName = loginVol.getVolIC();  //Assign user entered values to temporary
        String password = loginVol.getPassword();
        
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String userNameDB = "";
        String passwordDB = "";
        
        //do a database connection
        try {
            con = DBConnection.createConnection();  //Fetch database connection object
            statement = con.createStatement();  //Statement is used to write queries
            resultSet = statement.executeQuery("SELECT VOLUNTEERIC, VOLUNTEERPASS FROM VOLUNTEER"); 
            /*String sql = "SELECT VOLUNTEERIC, VOLUNTEERPASS FROM VOLUNTEER WHERE VOLUNTEERIC=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, userName);
            resultSet = pstmt.executeQuery();*/
            
            while (resultSet.next())  //Until next row is present otherwise it returns false
            {
                userNameDB = resultSet.getString(1);   //fetch the values present in database
                passwordDB = resultSet.getString(2);
                
                if(userName.equals(userNameDB) &&  password.equals(passwordDB)) {
                    return "SUCCESS";   //If the user entered values are already present in the database
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials!";  //Return appropriate message in case of failure
    }
    
    
    
    //Register Volunteer
    public String insertUser (Volunteer regVol){
        
        String volName = regVol.getVolName();  
        String volIC = regVol.getVolIC();
        String phoneNo = regVol.getPhoneNo();
        String email = regVol.getEmail();
        String passw = regVol.getPassword();
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String userNameDB = "";
        String passwordDB = "";
        
        try {
            con = DBConnection.createConnection();
            String query = "INSERT INTO VOLUNTEER (VOLUNTEERIC,VOLUNTEERNAME,VOLUNTEERPHONE,VOLUNTEEREMAIL,VOLUNTEERPASS) values (?,?,?,?,?)";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, volIC);
            pstmt.setString(2, volName);
            pstmt.setString(3, phoneNo);
            pstmt.setString(4, email);
            pstmt.setString(5, passw);
            
            int i = pstmt.executeUpdate();
            
            if(i != 0)
                return "SUCCESSFUL";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "REGISTRATION FAILED";
    }
}
