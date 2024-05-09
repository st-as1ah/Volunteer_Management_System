/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vol.controller;

import com.vol.dao.VolunteerDAO;
//import com.vol.model.LoginVolunteer;
import com.vol.model.Volunteer;
import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginVolServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            String userName = request.getParameter("volIC");
            String password = request.getParameter("password");
            
            Volunteer loginVol = new Volunteer();
            loginVol.setVolIC(userName);
            loginVol.setPassword(password);
            
            VolunteerDAO loginDao = new VolunteerDAO(); //creating object 
            String userValidate = loginDao.authenticateUser(loginVol);
            
            if (userValidate.equals("SUCCESS"))
            {
                request.getSession().setAttribute("volIC", userName);
                request.getSession().setAttribute("volunteer",loginVol);
                request.getRequestDispatcher("/VolunteerDetails2.jsp").forward(request, response);
            }
            else
                if (userValidate.equals("Invalid user credentials!"))
            {
                request.setAttribute("errMessage", "Failed to login");
                request.getRequestDispatcher("/VolunteerLogin2.jsp").forward(request, response);

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
