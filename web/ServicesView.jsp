<%-- 
    Document   : viewServicesFClerk
    Created on : Feb 05, 2022, 8:28:00 PM
    Author     : AdibShahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver"
                   url="jdbc:derby://localhost:1527/VolunteerDB" user="APP"
                   password="abc123"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Services Details</title>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />
        <div class="an-box">
            <div class="an-box-in b">
                <h3>View Services Details</h3>
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
                    SELECT SERVICES.SERVICESID,SERVICES.SERVICESNAME,SERVICES.SERVICESDESC,STAFF.STAFFNAME
                    FROM SERVICES 
                    JOIN STAFF
                    ON SERVICES.STAFFID=STAFF.STAFFID
                </sql:query>

                <table id="history">
                    <!-- column headers -->
                    <tr>
                        <th>Services ID</th>
                        <th>Services Name</th>
                        <th>Services Description</th>
                        <th>Staff Name</th>
                        <th>Action</th>
                    </tr>
                    <!-- column data -->
                    <% int count = 0; %>
                    <c:forEach var="row" items="${result.rowsByIndex}">
                        <tr>
                            <c:forEach var="column" items="${row}">
                                <td><c:out value="${column}"/></td>
                            </c:forEach>
                            <td><form action="ServicesUpdate.jsp" method="POST">
                                    <input type="hidden" name="servicesid" value="${row[0]}"/> 
                                    <input type="submit" value="Update" class="h-btn u"/>
                                </form>
                            </td>
                        </tr>
                        <% count++;%>
                    </c:forEach>
                </table>
                <div class="history-total">
                    Total : <%=count%>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
