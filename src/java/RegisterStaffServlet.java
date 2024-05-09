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
public class RegisterStaffServlet extends HttpServlet {

    
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
        
        List errorMsgs = new LinkedList();
        try{
        String staffIC = request.getParameter("staffIC");
        String staffName = request.getParameter("staffName");
        String staffPhone = request.getParameter("staffPhone");
        String staffPass = request.getParameter("staffPass");
        String staffConfirmPass = request.getParameter("staffConfirmPass");
        
        if(staffIC == null || staffIC.trim().length()==0) {
            errorMsgs.add("Please enter IC number.");
        }
            if(staffName == null || staffName.trim().length()==0) {
                errorMsgs.add("Please enter your name.");
        } else if(staffPhone == null || staffPhone.trim().length()==0) {
            errorMsgs.add("Please enter your phone number.");
        } else if(staffPass == null || staffPass.trim().length()==0) {
            errorMsgs.add("Please enter password.");
        } else if(!staffPass.equals(staffConfirmPass)) {
            errorMsgs.add("Please confirm password needs to match with password.");
        }
            if(!errorMsgs.isEmpty()){
                request.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("StaffList.jsp");
                view.forward(request, response);
                return;
            }
        Staff registerBean = new Staff();
        
        registerBean.setStaffIC(staffIC);
        registerBean.setStaffName(staffName);
        registerBean.setStaffPhone(staffPhone);
        registerBean.setStaffPass(staffPass);
        
        StaffDao registerDao = new StaffDao();
        
        String userRegister = registerDao.addStaff(registerBean);
        HttpSession hs = request.getSession();
        if(userRegister.equals("SUCCESS")){
            hs.setAttribute("sic", request.getParameter("staffIC"));
            hs.setAttribute("sname", request.getParameter("staffName"));
            hs.setAttribute("sphone", request.getParameter("staffPhone"));
            hs.setAttribute("spass", request.getParameter("staffPass"));
            //Redirecting admin to dashboard page
            response.sendRedirect("StaffList.jsp");

            
        }
        else{
            //If details are wrong
                String message = "Failed to add staff";
                hs.setAttribute("fail-message", message);
                //Redirecting admin to admin login page
                response.sendRedirect("StaffAdd.jsp");
         }
        }catch (RuntimeException e){
            errorMsgs.add("An unexpected error: "+ e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getRequestDispatcher("StaffAdd.jsp");
            view.forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
