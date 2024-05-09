/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vol.controller;

import com.vol.dao.VolunteerDAO;
import com.vol.model.Volunteer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asiah
 */
public class RegisterVolServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            String volName = request.getParameter("volName");
            String volIC = request.getParameter("volIC");
            String phoneNo = request.getParameter("phoneNo");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPass = request.getParameter("confirm_pwd");
            
            if(!password.equals(confirmPass))
            {
                request.setAttribute("errMessage","Password and Confirm Password is not same.");
                request.getRequestDispatcher("/VolunteerRegister2.jsp").forward(request, response); 
            }
            
            Volunteer regVol = new Volunteer();
            regVol.setVolName(volName);
            regVol.setVolIC(volIC);
            regVol.setPhoneNo(phoneNo);
            regVol.setEmail(email);
            regVol.setPassword(password);
            
            VolunteerDAO regdao = new VolunteerDAO();
            String registervol = regdao.insertUser(regVol);
            
            if(registervol.equals("SUCCESSFUL"))
            {
                request.setAttribute("errMessage", "Successfully Registered!");
                request.getRequestDispatcher("/VolunteerLogin2.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("errMessage", registervol);
                request.getRequestDispatcher("/VolunteerRegister2.jsp").forward(request, response);
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
