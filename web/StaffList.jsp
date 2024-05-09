<%-- 
    Document   : staffList
    Created on : Feb 5, 2022, 4:09:48 AM
    Author     : User
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="javax.servlet.http.*"%>
<!DOCTYPE html>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver"
                   url="jdbc:derby://localhost:1527/VolunteerDB" user="APP"
                   password="abc123"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        
        <title>List of Staff</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            table {
             font-family: arial, sans-serif;
             border-collapse: collapse;
             width: 100%;
            }

            td, th {
             border: 1px solid #dddddd;
             text-align: left;
             padding: 8px;
            }

            tr:nth-child(even) {
             background-color: #dddddd;
            }
    </style>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />
        <%
            //Checking whether admin in session or not
            //String staffIC = (String) session.getAttribute("staffIC");
            //if (session.getAttribute("staffIC") != null && session.getAttribute("staffIC") != "") {
                
        %>
        <div class="an-box">
            <div class="an-box-in b">
                <h3>List of Staff</h3>
                <c:if test="${not empty errorMsgs}">
                    <font color='white'>
                    <p>Report</p>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li><p>${message}</p></li>
                                </c:forEach>
                    </ul>
                    </font>
                </c:if>
                <sql:query var="result" dataSource="${myDatasource}">
                    SELECT STAFFID, STAFFIC, STAFFNAME, STAFFPHONE
                    FROM STAFF
                </sql:query>
                <table id="history">
            <!--column header-->
                 <tr>
                    <th>ID No</th>
                    <th>Identity Card</th>
                    <th>Name</th>
                    <th>Phone Number</th>
                    <th>Actions</th>
                </tr>
                <!-- column data -->
                <c:forEach var="row" items="${result.rowsByIndex}">
                    <tr>
                        <c:forEach var="column" items="${row}">
                                <td><c:out value="${column}"/></td>
                        </c:forEach>
                        <td>
                            <form action="DeleteStaffServlet" method="POST">
                                <input type="hidden" name="staffID" value="${row[0]}"/>  
                                <input type="submit" value="Delete" onclick="deleteStaff()" class="h-btn d"/>
                            </form>
                        </td>   
                    </tr>
                </c:forEach>
                    
                </table>
            </div>
        </div>
	<script>
		function deleteStaff() {
		  var txt;
		  if (confirm("Are you sure you want to delete? ")) {
			alert("Deleted succesfully !");
		  } else {
			txt = "You pressed Cancel!";
		  }
		  document.getElementById("demo").innerHTML = txt;
		}
        </script>
    </body>
</html>
