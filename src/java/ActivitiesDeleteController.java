/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Volunteer.bean.Activities;
import Volunteer.bean.ActivitiesDesc;
import Volunteer.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fatin Amalin
 */
public class ActivitiesDeleteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<String> errorMsgs=new LinkedList();        
        try(PrintWriter out=response.getWriter()){
            int actID=Integer.parseInt(request.getParameter("actid"));
                try{

                   Connection con=DBConnection.createConnection();

                       request.setAttribute("actID",actID);
                       PreparedStatement pstmt=con.prepareStatement("Delete from ACTIVITIESDESC WHERE ACTID=?");
                       pstmt.setInt(1, actID);
                       pstmt.executeUpdate();

                       PreparedStatement prstmt=con.prepareStatement("Delete from ACTIVITIES WHERE ACTID=?");
                       prstmt.setInt(1, actID);
                       prstmt.executeUpdate();

                        pstmt.close();
                        prstmt.close();
                        con.close();  
                        errorMsgs.add("Successfully delete activities " + actID);
                        request.setAttribute("errorMsgs", errorMsgs);
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/ActivitiesViewFStaff.jsp");
                      
                        view.forward(request,response);
               }

                catch (IOException | SQLException | ServletException ex){   
                    ex.printStackTrace();
                    out.println("An unexpected error: " + ex.getMessage());
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
