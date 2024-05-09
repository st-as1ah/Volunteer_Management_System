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
public class ActivitiesUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
        List<String> errorMsgs=new LinkedList();
        
        try(PrintWriter out=response.getWriter()){
            int actID=Integer.parseInt(request.getParameter("actid"));
            String actName=request.getParameter("actName");
            String actDesc=request.getParameter("actDesc");
            String actLocation=request.getParameter("actLocation");
            String serviceName=request.getParameter("serviceName");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String date =request.getParameter("actDate");
            Date actDate=null;
            
            if(actName==null ||  actName.trim().length()==0) {
                errorMsgs.add("Please enter activity name");
            }

            if(actLocation==null  ||  actLocation.trim().length()==0) {
                errorMsgs.add("Please enter activity location");
            }
            
            if(date==null ||  actName.trim().length()==0) {
                errorMsgs.add("Please enter activity date");
            } else
                    if(date!=null) {
                        actDate = formatter.parse(date);
                    }
            
            if(errorMsgs.isEmpty())   
            {   
                try{
                    
                   Connection con=DBConnection.createConnection();
                  
                   PreparedStatement pst=con.prepareStatement("Select SERVICESID from SERVICES WHERE SERVICESNAME=?");
                   pst.setString(1,serviceName);
                   ResultSet rst=pst.executeQuery();
                   
                   int serviceid=0;
                   while(rst.next())
                   {
                        serviceid=rst.getInt("SERVICESID"); 

                    }
                  
                       Activities act=new Activities();
                       act.setActID(actID);
                       act.setActName(actName);
                       act.setActDesc(actDesc);
                       
                       ActivitiesDesc activeDesc=new ActivitiesDesc();
                       activeDesc.setActID(actID);
                       activeDesc.setActLocation(actLocation);
                       activeDesc.setActDate(actDate);
                       
                    
                       PreparedStatement pstmt=con.prepareStatement("UPDATE ACTIVITIES SET ACTNAME=?, ACTDESC=?, SERVICESID=? WHERE ACTID=?");
                       pstmt.setString(1,actName);
                       pstmt.setString(2,actDesc);
                       pstmt.setInt(3,serviceid);
                       pstmt.setInt(4, actID);
                       pstmt.executeUpdate();

                       PreparedStatement prstmt=con.prepareStatement("UPDATE ACTIVITIESDESC SET ACTLOCATION=?, ACTDATE=? WHERE ACTID=?");
                       prstmt.setString(1,actLocation);
                       prstmt.setDate(2, new java.sql.Date(actDate.getTime()));
                       prstmt.setInt(3, actID);
                       prstmt.executeUpdate();
                       
                        errorMsgs.add("Successfully update activities " + actID);
                        request.setAttribute("errorMsgs", errorMsgs);
                        
                        RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesViewFStaff.jsp");
                        view.forward(request,response);
                        pst.close();
                        rst.close();
                        pstmt.close();
                        prstmt.close();
                        con.close();  
               }

                catch (IOException | NumberFormatException | SQLException | ServletException ex){   
                    ex.printStackTrace();
                     request.setAttribute("actid",actID);
                    errorMsgs.add("An unexpected error: " + ex.getMessage());
                    request.setAttribute("errorMsgs", errorMsgs);
                    RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesUpdate.jsp");
                    view.forward(request, response); 
                }
            }
         
            else{
                 request.setAttribute("actid",actID);
               request.setAttribute("errorMsgs",errorMsgs);
               RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesUpdate.jsp");
               view.forward(request,response);  
            }
        }catch(Exception e){   
            e.printStackTrace();
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getServletContext().getRequestDispatcher("/ActivitiesUpdate.jsp");
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
private Object SimpleDateFormat(String ddMMyyyy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
