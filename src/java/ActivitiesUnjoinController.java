

import Volunteer.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 *
 * @author Fatin Amalin
 */
public class ActivitiesUnjoinController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<String> errorMsgs=new LinkedList();  
        
        try (PrintWriter out = response.getWriter()) {
         
            int actID=Integer.parseInt(request.getParameter("actid"));
                try{

                   Connection con=DBConnection.createConnection();

                       request.setAttribute("actID",actID);
                       PreparedStatement pstmt=con.prepareStatement("Update ACTIVITIESDESC SET VOLUNTEERIC=? WHERE ACTID=?");
                       pstmt.setString(1,null);
                       pstmt.setInt(2, actID);
                       pstmt.executeUpdate();


                        pstmt.close();
                        con.close();  
                        errorMsgs.add("Successfully unjoin activities " + actID);
                        request.setAttribute("errorMsgs", errorMsgs);
                        
                        RequestDispatcher view = getServletContext().getRequestDispatcher("/ActivitiesViewFVolunteer.jsp");
                       
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

