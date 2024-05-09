<%-- 
    Document   : RegisterStaff
    Created on : Feb 5, 2022, 7:22:25 PM
    Author     : User
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver"
                   url="jdbc:derby://localhost:1527/VolunteerDB" user="APP"
                   password="abc123"/>
<html>
    <head>
        <title>Add Staff</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />
            <div class="an-box b">
                <div class="an-box-in b">
                    <h3>Add Staff</h3>
                    <div class="an-form">
                        <form name="form" action="RegisterStaffServlet" method="POST">
                            <table id="newcert">
                                <font color="white"><%= (request.getAttribute("errorMsgs")== null)? ""
                            : request.getAttribute("errorMsgs")%></font>
                                <tr>
                                    <td>Identity Card Number</td>
                                    <td><input type="text" name="staffIC" placeholder="Eg:991010101010" size="40" maxlength="12" minlength="12" required></td>
                                </tr>
                                <tr>
                                    <td>Name</td>
                                    <td><input type="text" name="staffName" placeholder="" size="40" required></td>
                                </tr>
                                <tr>
                                    <td>Phone Number</td>
                                    <td><input type="text" name="staffPhone" placeholder="Eg:0123456789" size="40" maxlength="11" minlength="10" required></td>
                                </tr>
                                <tr>
                                    <td>Password</td>
                                    <td><input type="password" name="staffPass" placeholder="" size="40"  required></td>
                                </tr>
                                <tr>
                                    <td>Confirm Password</td>
                                    <td><input type="password" name="staffConfirmPass" placeholder="" size="40" required></td>
                                </tr>
                            <tr>
                                <td>
                                    <div class="an-btn-box">
                                        <input type="reset" value="Reset">
                                    </div>
                                </td>
                                <td>
                                    <div class="an-btn-box">
                                        <input type="submit" value="Submit" name="submit">
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <!--<a href="loginStaff.jsp">Home</a>-->
                        </form>
                    </div>
            </div>
        </div>
    </div>
    </body>
</html>
