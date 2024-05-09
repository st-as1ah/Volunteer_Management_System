/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Volunteer.bean.*;
import Volunteer.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AdibShahar
 */
public class ServicesAddController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
        List<String> errorMsgs=new LinkedList();
        
        try(PrintWriter out=response.getWriter()){
            String staffname=request.getParameter("staffname");
            String servicesName=request.getParameter("services_Name");
            String servicesDesc=request.getParameter("services_Desc");

            if(servicesName==null ||  servicesName.trim().length()==0) {
                errorMsgs.add("Please enter a services name");
            }


            if(errorMsgs.isEmpty())   
            {   
                try{
                    
                   Connection con=DBConnection.createConnection();
                   PreparedStatement ps=con.prepareStatement("Select max(SERVICESID)+1 from SERVICES");
                   ResultSet rs=ps.executeQuery();
                   int id=0;
                   while(rs.next())
                   {
                        id=Integer.parseInt(rs.getString(1)); 

                   }
                   PreparedStatement pst=con.prepareStatement("Select STAFFID from STAFF WHERE STAFFNAME=?");
				   pst.setString(1,staffname);
                   ResultSet rst=pst.executeQuery();
                   
                   int stafid=0;
                   while(rst.next())
                   {
                        stafid=rst.getInt("STAFFID"); 

                    }
                  
                       Services serv=new Services();
                       serv.setServicesID(id);
                       serv.setServicesName(servicesName);
                       serv.setServicesDesc(servicesDesc);
					   
                       request.setAttribute("StaffID",stafid);
                       request.setAttribute("Services",serv);
                       PreparedStatement pstmt=con.prepareStatement("INSERT into SERVICES(SERVICESNAME,SERVICESDESC,STAFFID) Values (?,?,?)");
                       pstmt.setString(1,servicesName);
                       pstmt.setString(2,servicesDesc);
                       pstmt.setInt(3,stafid);
                       pstmt.executeUpdate();
            
                        ps.close();
                        pst.close();
                        rs.close();
                        rst.close();
                        pstmt.close();
                        con.close();  
                        
                        errorMsgs.add("Successfully update activities " + id);
                        request.setAttribute("errorMsgs", errorMsgs);
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/ServicesView.jsp");
                        
                        view.forward(request,response);
               }
                catch (IOException | NumberFormatException | SQLException | ServletException ex){   
                    ex.printStackTrace();
                    errorMsgs.add("An unexpected error: " + ex.getMessage());
                    request.setAttribute("errorMsgs", errorMsgs);
                    RequestDispatcher view = request.getRequestDispatcher("ServicesAdd.jsp");
                    view.forward(request, response); 
                }
            }
         
            else{  
               request.setAttribute("errorMsgs",errorMsgs);
               RequestDispatcher view = request.getRequestDispatcher("/ServicesAdd.jsp");
               view.forward(request,response);  
            }
        }catch(Exception e){   
            e.printStackTrace();
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getRequestDispatcher("/ServicesAdd.jsp");
            view.forward(request, response); 
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