/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Volunteer.bean.Services;
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
import Volunteer.util.*;

/**
 *
 * @author AdibShahar
 */
public class ServicesUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
        List<String> errorMsgs=new LinkedList();
        
        try(PrintWriter out=response.getWriter()){
            int servicesID=Integer.parseInt(request.getParameter("servicesid"));
            String servicesName=request.getParameter("servicesName");
            String servicesDesc=request.getParameter("servicesDesc");
            String staffname=request.getParameter("staffname");

            if(servicesName==null ||  servicesName.trim().length()==0) {
                errorMsgs.add("Please enter services name.");
            }


            if(errorMsgs.isEmpty())   
            {   
                try{
                    
                   Connection con=DBConnection.createConnection();
                  
                   PreparedStatement pst=con.prepareStatement("Select STAFFID from STAFF WHERE STAFFNAME=?");
				   pst.setString(1,staffname);
                   ResultSet rst=pst.executeQuery();
                   
                   int stafid=0;
                   while(rst.next())
                   {
                        stafid=rst.getInt("STAFFID"); 

                    }
                  
                       Services serv=new Services();
                       serv.setServicesID(servicesID);
                       serv.setServicesName(servicesName);
                       serv.setServicesDesc(servicesDesc);                              
                      
                       request.setAttribute("Services",serv);
                       PreparedStatement pstmt=con.prepareStatement("UPDATE SERVICES SET SERVICESNAME=?, SERVICESDESC=?, STAFFID=? WHERE SERVICESID=?");
                       pstmt.setString(1,servicesName);
                       pstmt.setString(2,servicesDesc);
                       pstmt.setInt(3,stafid);
                       pstmt.setInt(4, servicesID);
                       pstmt.executeUpdate();
                       
                       errorMsgs.add("Successfully update services " + servicesID);
                        request.setAttribute("errorMsgs", errorMsgs);
                        RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ServicesView.jsp");
                        view.forward(request,response);
                        pst.close();
                        rst.close();
                        pstmt.close();
                        con.close();  
               }

                catch (IOException | NumberFormatException | SQLException | ServletException ex){   
                    ex.printStackTrace();
                    request.setAttribute("servicesid",servicesID);
                    errorMsgs.add("An unexpected error: " + ex.getMessage());
                    request.setAttribute("errorMsgs", errorMsgs);
                    RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ServicesUpdate.jsp");
                    view.forward(request, response); 
                }
            }
         
            else{
                 request.setAttribute("servicesid",servicesID);
               request.setAttribute("errorMsgs",errorMsgs);
               RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ServicesUpdate.jsp");
               view.forward(request,response);  
            }
        }catch(Exception e){   
            e.printStackTrace();
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ServicesUpdate.jsp");
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
