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
public class PassUpdateVolServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PassUpdateVolServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PassUpdateVolServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        
        List errMessage = new LinkedList();
        
        String volIC = request.getParameter("volIC");
        String passw = request.getParameter("password");
        String conPass = request.getParameter("confirm_pwd");
        
        if(!passw.equals(conPass))
        {
            errMessage.add("Password and Confirm Password not match!");
        }
        if (!errMessage.isEmpty()) {
            request.setAttribute("errMessage", errMessage);
            RequestDispatcher view = getServletContext().getRequestDispatcher("/VolunteerPassword2.jsp");
            view.forward(request, response);
            return;
        }
        
        //connect to database
            Connection con = null;
            String vIC = "";
            String pass = "";
            
            try {
                con = DBConnection.createConnection();
                
                Statement stat = con.createStatement();
                ResultSet res = stat.executeQuery("SELECT VOLUNTEERIC, VOLUNTEERPASS FROM VOLUNTEER WHERE VOLUNTEERIC='"+volIC+"'");
                
                while(res.next()) {
                    vIC = res.getString(1);
                    pass = res.getString(2);
                }
                
                if(passw.equals(conPass) && vIC.equals(volIC)) 
                {
                    PreparedStatement pstmt2 = con.prepareStatement("UPDATE VOLUNTEER SET VOLUNTEERPASS=? WHERE VOLUNTEERIC=?");
                    pstmt2.setString(1, passw);
                    pstmt2.setString(2, volIC);
                    pstmt2.executeUpdate();

                    int k = pstmt2.executeUpdate();
                    if(k>0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("volIC", volIC);
                        request.setAttribute("errMessage", "Password successfully updated");
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/VolunteerPassword2.jsp");
                        view.forward(request, response);
                        return;
                    }
                }
                
                if(!vIC.equals(volIC))
                {
                    request.setAttribute("errMessage", "User does not exist!");
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/VolunteerPassword2.jsp");
                    view.forward(request, response);
                    return;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                errMessage.add("An unexpected error: " + e.getMessage());
                request.setAttribute("errMessage", errMessage);
                RequestDispatcher view = getServletContext().getRequestDispatcher("/VolunteerPassword2.jsp");
                view.forward(request, response);
                return;
            }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
