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
 * @author Fatin Amalin
 */
public class ActivitiesAddController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<String> errorMsgs = new LinkedList();

        try (PrintWriter out = response.getWriter()) {

            String actName = request.getParameter("act_Name");
            String actDesc = request.getParameter("act_Desc");
            String actLocation = request.getParameter("act_Loc");
            String serviceName = request.getParameter("service_Name");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String date = request.getParameter("act_Date");
            Date actDate;
            actDate = null;

            if (actName == null || actName.trim().length() == 0) {
                errorMsgs.add("Please enter activity name");
            }

            if (actLocation == null || actLocation.trim().length() == 0) {
                errorMsgs.add("Please enter activity location");
            }

            if (date == null || actName.trim().length() == 0) {
                errorMsgs.add("Please enter activity date");
            } else if (date != null) {
                actDate = formatter.parse(date);
            }

            if (errorMsgs.isEmpty()) {
                try {

                    Connection con = DBConnection.createConnection();
                    PreparedStatement ps = con.prepareStatement("Select max(ACTID)+1 from ACTIVITIES");
                    ResultSet rs = ps.executeQuery();
                    int id = 0;
                    while (rs.next()) {
                        id = Integer.parseInt(rs.getString(1));

                    }
                    //Get the servicesid from dropdown serviceName
                    PreparedStatement pst = con.prepareStatement("Select SERVICESID from SERVICES WHERE SERVICESNAME=?");
                    pst.setString(1, serviceName);
                    ResultSet rst = pst.executeQuery();

                    int serviceid = 0;
                    while (rst.next()) {
                        serviceid = rst.getInt("SERVICESID");

                    }
                      
                    Activities act = new Activities();
                    act.setActID(id);
                    act.setActName(actName);
                    act.setActDesc(actDesc);

                    ActivitiesDesc activeDesc = new ActivitiesDesc();
                    activeDesc.setActID(id);
                    activeDesc.setActLocation(actLocation);
                    activeDesc.setActDate(actDate);

                    request.setAttribute("Activities", act);
                    request.setAttribute("ActivitiesDesc", activeDesc);
                    request.setAttribute("ServiceName", serviceName);
                    PreparedStatement pstmt = con.prepareStatement("INSERT into ACTIVITIES(ACTNAME,ACTDESC,SERVICESID) Values (?,?,?)");
                    pstmt.setString(1, actName);
                    pstmt.setString(2, actDesc);
                    pstmt.setInt(3, serviceid);
                    pstmt.executeUpdate();

                    PreparedStatement prstmt = con.prepareStatement("INSERT into ACTIVITIESDESC(ACTID,ACTLOCATION,ACTDATE) Values (?,?,?)");
                    prstmt.setInt(1, id);
                    prstmt.setString(2, actLocation);
                    prstmt.setDate(3, new java.sql.Date(actDate.getTime()));
                    prstmt.executeUpdate();

                    ps.close();
                    pst.close();
                    rs.close();
                    rst.close();
                    pstmt.close();
                    prstmt.close();
                    con.close();

                    errorMsgs.add("Successfully add activities " + id);
                    request.setAttribute("errorMsgs", errorMsgs);
                    RequestDispatcher view = getServletContext().getRequestDispatcher("/ActivitiesViewFStaff.jsp");
                    
                    view.forward(request, response);
                
                } catch (IOException | NumberFormatException | SQLException | ServletException ex) {
                    ex.printStackTrace();
                    errorMsgs.add("An unexpected error: " + ex.getMessage());
                    request.setAttribute("errorMsgs", errorMsgs);
                    RequestDispatcher view = request.getRequestDispatcher("/ActivitiesAdd.jsp");
                    view.forward(request, response);
                }
            
            } else {
                request.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher view = request.getRequestDispatcher("/ActivitiesAdd.jsp");
                view.forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getRequestDispatcher("/ActivitiesAdd.jsp");
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
