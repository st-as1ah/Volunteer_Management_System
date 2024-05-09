/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author User
 */
public class StaffDao {
    
    public String authenticateUser(Staff loginBean){
        String staffIC = loginBean.getStaffIC();
        String staffPass = loginBean.getStaffPass();
        
        Connection con=null;
        ResultSet rs = null;
        Statement stmt=null;
        String icDB = "";
        String passwordDB = "";
        
        try{
            con = DBConnection.createConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select staffic, staffpass from staff");
            while(rs.next()){
                icDB = rs.getString("staffic");
                passwordDB = rs.getString("staffpass");
                if(staffIC.equals(icDB) && staffPass.equals(passwordDB)){
                    return "SUCCESS";
                }
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }
    
    public String addStaff(Staff registerBean){
        String staffIC = registerBean.getStaffIC();
        String staffName = registerBean.getStaffName();
        String staffPhone = registerBean.getStaffPhone();
        String staffPass = registerBean.getStaffPass();
        
        Connection con=null;
        PreparedStatement pstmt=null;
       
        try{
            con = DBConnection.createConnection();
            String query = "insert into staff (staffic, staffname, staffphone, staffpass) values (?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, staffIC);
            pstmt.setString(2, staffName);
            pstmt.setString(3, staffPhone);
            pstmt.setString(4, staffPass);
            
            int i = pstmt.executeUpdate();
            
            if(i != 0)
                return "SUCCESS";
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Something went wrong..";
    }
    
    public String updateStaff(Staff st){  
        int status=0;  
        try{  
            Connection con=DBConnection.createConnection(); 
            PreparedStatement ps=con.prepareStatement(  
                         "update staff set staffic=?,staffname=?,staffphone=?,staffpass=? where staffid=?");  
            ps.setString(1,st.getStaffIC());  
            ps.setString(2,st.getStaffName());  
            ps.setString(3,st.getStaffPhone());  
            ps.setString(4,st.getStaffPass());  
            ps.setInt(5,st.getStaffID());  
              
            status=ps.executeUpdate();  
              
            if(status != 0)
                return "SUCCESS";
            
        }catch(SQLException ex){ex.printStackTrace();}  
          
        return "Something went wrong..";  
    }  
    
     public String deleteStaff(int id){  
        int status=0;  
        try{  
            Connection con=DBConnection.createConnection();  
            PreparedStatement ps=con.prepareStatement("delete from staff where staffid=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
            if(status != 0)
                return "SUCCESS";
             
        }catch(SQLException e){e.printStackTrace();}  
          
        return "Something went wrong..";  
    }
     
    public Staff getStaffById(int id){  
        Staff staff=new Staff();  
          
        try{  
            Connection con=DBConnection.createConnection();  
            PreparedStatement ps=con.prepareStatement("select * from staff where staffid=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                staff.setStaffID(rs.getInt(1));  
                staff.setStaffIC(rs.getString(2));  
                staff.setStaffName(rs.getString(3));  
                staff.setStaffPhone(rs.getString(4));  
                staff.setStaffPass(rs.getString(5));  
            }  
            con.close();  
        }catch(SQLException ex){ex.printStackTrace();}  
          
        return staff;  
    }
    
    public List<Staff> getAllStaff(){  
        List<Staff> staffList=new ArrayList<Staff>();  
          
        try{  
            Connection con=DBConnection.createConnection();  
            PreparedStatement ps=con.prepareStatement("select * from staff");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Staff st=new Staff();  
                st.setStaffID(rs.getInt(1));  
                st.setStaffIC(rs.getString(2));  
                st.setStaffName(rs.getString(3));  
                st.setStaffPhone(rs.getString(4));
                staffList.add(st);  
            }  
            rs.close();
            ps.close();
            con.close();  
        }catch(SQLException e){e.printStackTrace();}  
          
        return staffList;  
    }  
}
