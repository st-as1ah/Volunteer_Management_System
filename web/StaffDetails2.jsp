<%-- 
    Document   : StaffUpdate
    Created on : Feb 6, 2022, 6:05:09 AM
    Author     : User
--%>
<%@page import="bean.Staff"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver"
                   url="jdbc:derby://localhost:1527/VolunteerDB" user="APP"
                   password="abc123"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Staff Details</title>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />
            <div class="an-box b">
                <div class="an-box-in c">
                    <h3>My Profile</h3>
                    
                    <sql:query var="result" dataSource="${myDatasource}">
                        SELECT * FROM STAFF WHERE STAFFIC=?
                        <sql:param value="${staffIC}"/>
                    </sql:query>

                    <c:forEach var="row" items="${result.rows}">
                        <c:set var="staffID" value="${row.staffid}"/>
                        <c:set var="staffIC" value="${row.staffic}"/>
                        <c:set var="staffName" value="${row.staffname}"/>
                        <c:set var="staffPhone" value="${row.staffphone}"/>
                        
                    </c:forEach>
                    <div class="an-form">
                    <form method="">
                        <table id="update" cellpadding="4" cellspacing="4">
                            <tr>
                            <b><font color="white"><%= (request.getAttribute("errorMsgs")== null)? ""
                                        : request.getAttribute("errorMsgs")%></font></b>
                            </tr>
                            <tr>
                                <td>ID</td>
                                <td><input type="text" name="staffID" value="${staffID}" disabled></td>
                            </tr>
                            <tr>
                                <td>IC No</td>
                                <td><input type="text" name="staffIC" value="${staffIC}" disabled></td>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td><input type="text" name="staffName" value="${staffName}" disabled=""></td>
                            </tr>
                            <tr>
                                <td>Phone Number</td>
                                <td><input type="text" name="staffPhone" value="${staffPhone}" disabled=""></td>
                            </tr>
                            <tr>
                                <td></td>

                            </tr>
                        </table>
                    </form>    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
