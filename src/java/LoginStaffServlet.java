/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.Staff;

import dao.StaffDao;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Najihah
 */
public class LoginStaffServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginStaff</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginStaff at " + request.getContextPath() + "</h1>");
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
        
        String IC = request.getParameter("staffic");
        String password = request.getParameter("staffpass");
        
        
        Staff bean = new Staff();
        bean.setStaffIC(IC);
        bean.setStaffPass(password);
        request.setAttribute("bean", bean);
        
        StaffDao loginDao = new StaffDao(); //create obj for LoginDao
        String userValidate = loginDao.authenticateUser(bean);
        HttpSession session = request.getSession();
        if(userValidate.equals("SUCCESS")){
            session.setAttribute("staffIC", IC);
            response.sendRedirect("StaffDetails2.jsp");
        }else{
            //if details wrong
            String message = "You have enter wrong credentials";
                session.setAttribute("credential", message);
                //Redirecting admin to admin login page
                response.sendRedirect("StaffLogin.jsp");

        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
