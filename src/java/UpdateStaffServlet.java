/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.Staff;
import dao.StaffDao;
import util.DBConnection;
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
 * @author User
 */
public class UpdateStaffServlet extends HttpServlet {

   
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        List errMessage = new LinkedList();
        
        try (PrintWriter out = response.getWriter()) {
            
            int id = Integer.parseInt(request.getParameter("staffID"));
            String ic = request.getParameter("staffIC");
            String name = request.getParameter("staffName");
            String phone = request.getParameter("staffPhone");
            String passw = request.getParameter("staffPass");
            
            //Form verification
            if (name == null || name.trim().length() == 0) {
                errMessage.add("Please enter the name.");
            }
            if (phone == null || phone.trim().length() == 0) {
                errMessage.add("Please enter the phone number.");
            }
            if (ic == null || ic.trim().length() == 0) {
                errMessage.add("Please enter the ic.");
            }
            
            if (!errMessage.isEmpty()) {
                request.setAttribute("errorMsgs", errMessage);
                
                RequestDispatcher view = getServletContext().getRequestDispatcher("StaffUpdate.jsp");
                view.forward(request, response);
                return;
            }
            //connect to database
            Connection con = null;
            
            try {
                con = DBConnection.createConnection();
                
                String sql = "UPDATE STAFF SET STAFFIC=?, STAFFNAME=?,"
                        + "STAFFPHONE=?, STAFFPASS=? WHERE STAFFID=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                pstmt.setString(1, ic);
                pstmt.setString(2, name);
                pstmt.setString(3, phone);
                pstmt.setString(4, passw);
                pstmt.setInt(5, id);
                pstmt.executeUpdate();
                
                HttpSession session = request.getSession();
                session.setAttribute("staffIC", ic);
                request.setAttribute("errorMsgs", "Successfully updated");
                RequestDispatcher view = getServletContext().getRequestDispatcher("/StaffUpdate.jsp");
                view.forward(request, response);
                return;
                
         
            }
            catch (SQLException e) {
                e.printStackTrace();
                errMessage.add("An unexpected error: " + e.getMessage());
                request.setAttribute("errorMsgs", errMessage);
                request.setAttribute("staffIC", ic);
                request.getRequestDispatcher("StaffUpdate.jsp").forward(request, response);
                
                
            }
        }

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
