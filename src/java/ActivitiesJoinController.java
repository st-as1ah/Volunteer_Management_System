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
public class ActivitiesJoinController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
        List<String> errorMsgs=new LinkedList();
        
        try(PrintWriter out=response.getWriter()){
            int actID=Integer.parseInt(request.getParameter("actid"));
            String volunteerIC=request.getParameter("volic");


            if(errorMsgs.isEmpty())   
            {   
                try{
                    
                   Connection con=DBConnection.createConnection();



                       PreparedStatement prstmt=con.prepareStatement("UPDATE ACTIVITIESDESC SET VOLUNTEERIC=? WHERE ACTID=?");
                       prstmt.setString(1,volunteerIC);
                       prstmt.setInt(2, actID);
                       prstmt.executeUpdate();
                       
                        errorMsgs.add("Successfully join activities " + actID);
                        request.setAttribute("errorMsgs", errorMsgs);
                        RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesViewFVolunteer.jsp");
                        view.forward(request,response);

                        prstmt.close();
                        con.close();  
               }

                catch (IOException | NumberFormatException | SQLException | ServletException ex){   
                    ex.printStackTrace();
                    request.setAttribute("actid",actID);
                    errorMsgs.add("An unexpected error: " + ex.getMessage());
                    request.setAttribute("errorMsgs", errorMsgs);
                    RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesViewFVolunteer.jsp");
                    view.forward(request, response); 
                }
            }
         
            else{
                 request.setAttribute("actid",actID);
               request.setAttribute("errorMsgs",errorMsgs);
               RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesViewFVolunteer.jsp");
               view.forward(request,response);  
            }
        }catch(Exception e){   
            e.printStackTrace();
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesViewFVolunteer.jsp");
            view.forward(request, response); 
            return;
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
