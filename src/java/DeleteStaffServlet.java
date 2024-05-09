/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.Staff;
import dao.StaffDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBConnection;

/**
 *
 * @author Najihah
 */
public class DeleteStaffServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterStaffServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterStaffServlet at " + request.getContextPath() + "</h1>");
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
        
        int staffID=Integer.parseInt(request.getParameter("staffID"));
        Staff delBean = new Staff();
        
        delBean.setStaffID(staffID);
        StaffDao delDao = new StaffDao();
        
        String stDel = delDao.deleteStaff(staffID);
        HttpSession hs = request.getSession();
        if(stDel.equals("SUCCESS")){
            request.setAttribute("errorMsgs","Successfully deleted Staff.");
            //Redirecting admin to dashboard page
            response.sendRedirect("StaffList.jsp");

        }
        else{
            //If details are wrong
                String message = "Failed to delete staff";
                request.setAttribute("errorMsgs", message);
                //Redirecting admin to admin login page
                response.sendRedirect("StaffUpdate.jsp");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
