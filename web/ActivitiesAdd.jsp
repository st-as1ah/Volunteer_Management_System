<%-- 
    Document   : addActivities
    Created on : Jan 27, 2022, 4:31:40 PM
    Author     : Fatin Amalin
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
        <title>Add New Activities</title>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />
        <div class="an-box b">
            <div class="an-box-in b">
                <h3>Add New Activities</h3>
                <p>Fill in below!</p>
                <div class="an-form">   
                    <c:if test="${not empty errorMsgs}">
                        <font color='white'>
                        <br/><p>Error Report</p>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li><p>${message}</p></li>
                            </c:forEach>
                        </ul>
                        </font>
                    </c:if>

                    <form method="POST" action="ActivitiesAddController">
                        <table id="newcert">					
                            <tr>
                                <td>Activity Name</td>
                                <td><input type="text" size="60" name="act_Name" class="long"></td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td><input type="text" size="80" name="act_Desc" class="long"></td>
                            </tr>	
                            <tr>
                                <td>Activity Location</td>
                                <td><input type="text" size="60" name="act_Loc" class="long"></td>
                            </tr>							
                            <tr>
                                <td><label for="act_Date">Date</td>
                                <td><input type="date"  name="act_Date" required></td>        
                            </tr>
                            <tr>
                                <td><label for="service_Name">Service Category</label></td>
                                <td>
                                    <select id="type" name="service_Name">
                                        <sql:query var="result" dataSource="${myDatasource}">
                                            SELECT SERVICESNAME FROM SERVICES
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