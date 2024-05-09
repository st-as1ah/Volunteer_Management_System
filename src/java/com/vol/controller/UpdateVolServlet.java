/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vol.controller;

import com.vol.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asiah
 */
public class UpdateVolServlet extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List errMessage = new LinkedList();
        
        try (PrintWriter out = response.getWriter()) {
            
            String name = request.getParameter("volName");
            String volIC = request.getParameter("volIC");
            String phone = request.getParameter("phoneNo");
            String email = request.getParameter("email");
            
            //Form verification
            if (name.equals(null) || name.trim().length() == 0) {
                errMessage.add("Please enter the name.");
            }
            if (phone.equals(null) || phone.trim().length() == 0) {
                errMessage.add("Please enter the phone number.");
            }
            if (email.equals(null) || email.trim().length() == 0) {
                errMessage.add("Please enter the email.");
            }
            
            if (!errMessage.isEmpty()) {
                request.setAttribute("errMessage", errMessage);
                RequestDispatcher view = getServletContext().getRequestDispatcher("/VolunteerUpdate2.jsp");
                view.forward(request, response);
                return;
            }
            
            //connect to database
            Connection con = null;
            Statement stmt = null;
            ResultSet relSet = null;
            
            try {
                con = DBConnection.createConnection();
                
                String sql = "UPDATE VOLUNTEER SET VOLUNTEERNAME=?, VOLUNTEERPHONE=?,"
                        + "VOLUNTEEREMAIL=? WHERE VOLUNTEERIC=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                pstmt.setString(1, name);
                pstmt.setString(2, phone);
                pstmt.setString(3, email);
                pstmt.setString(4, volIC);
                pstmt.executeUpdate();

                pstmt.close();
                con.close();
                
                //Dispatch to Success view
                
                HttpSession session = request.getSession();
                session.setAttribute("volIC", volIC);
                request.setAttribute("errMessage", "Successfully updated");
                
                RequestDispatcher view = getServletContext().getRequestDispatcher("/VolunteerDetails2.jsp");
                view.forward(request, response);
                return;
            }
            catch (SQLException e) {
                e.printStackTrace();
                errMessage.add("An unexpected error: " + e.getMessage());
                request.setAttribute("errMessage", errMessage);
                request.setAttribute("volIC", volIC);
               
                RequestDispatcher view = getServletContext().getRequestDispatcher("/VolunteerUpdate2.jsp");
                view.forward(request, response);
                return;
            }
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
