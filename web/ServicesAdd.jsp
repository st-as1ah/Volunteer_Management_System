<%-- 
    Document   : addServices
    Created on : Feb 02, 2022, 10:04:20 PM
    Author     : AdibShahar
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<sql:setDataSource var="myDatasource" driver="org.apache.derby.jdbc.ClientDriver"
                   url="jdbc:derby://localhost:1527/VolunteerDB" user="APP"
                   password="abc123"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Services</title>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />
        <div class="an-box b">
            <div class="an-box-in b">
                <h3>Add New Services</h3>
                <p>Fill in below!</p>
                <div class="an-form">   
                    <c:if test="${not empty errorMsgs}">
                        <font color='white'>
                        <br/><p>Report</p>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li><p>${message}</p></li>
                            </c:forEach>
                        </ul>
                        </font>
                    </c:if>
                    <form method="POST" action="ServicesAddController">
                        <table id="newcert">				
                            <tr>
                                <td>Services Name</td>
                                <td><input type="text" size="60" name="services_Name"></td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td><textarea rows = "5" cols = "60" name="services_Desc"> </textarea></td>
                            </tr>							
                            <tr>
                                <td><label for="staffname">Staff Name</label></td>
                                <td>
                                    <select id="type" name="staffname">
                                        <sql:query var="result" dataSource="${myDatasource}">
                                            SELECT STAFFNAME FROM STAFF
                                        </sql:query>
                                        <c:forEach var="row" items="${result.rowsByIndex}">
                                            <c:forEach var="column" items="${row}">
                                                <option> 
                                                    <c:out value="${column}"/>
                                                </option>
                                            </c:forEach>
                                        </c:forEach>
                                    </select>
                                </td>
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
                    </form>      
                </div>
            </div>
        </div>
    </div>
</body>
</html>