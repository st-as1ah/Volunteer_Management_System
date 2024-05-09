




<%@page import="Volunteer.bean.Volunteer"%>
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
        <title>Join Activity</title>
    </head>
    <body>
        <jsp:include page="headerVolunteer.jsp" />
        <div class="an-box">
            <div class="an-box-in b">
                <h3>Join Activities</h3>
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
                <sql:query var="result" dataSource="${myDatasource}">
                    SELECT A.ACTID,A.ACTNAME,S.SERVICESNAME,A.ACTDESC,C.ACTLOCATION,C.ACTDATE
                    FROM ACTIVITIES A JOIN ACTIVITIESDESC C ON A.ACTID=C.ACTID 
                    JOIN SERVICES S ON A.SERVICESID=S.SERVICESID
                </sql:query>

                <table id="history">
                    <!-- column headers -->
                    <tr>
                        <th>Activities ID</th>
                        <th>Name</th>
                        <th>Service Category</th>
                        <th>Description</th>
                        <th>Location</th>
                        <th>Date</th>
                        <th>Action</th>
                    </tr>
                    <!-- column data -->
                    <% int count = 0; %>
                    <c:forEach var="row" items="${result.rowsByIndex}">
                        <tr>
                            <c:forEach var="column" items="${row}">
                                <td><c:out value="${column}"/></td>
                            </c:forEach>
                            <td>                       
                                    <form action="ActivitiesJoinController" method="POST">
                                        <input type="hidden" name="actid" value="${row[0]}"/>  
                                        <input type="hidden" name="volic" value="<%=session.getAttribute("volIC") %>"/>
                                        <input type="submit" value="Join"class="h-btn u" onClick="confirm()"/>
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
    <script>
        function confirm() {
            alert("Successfully Joined an Activity!");
        }
    </script>
</body>
</html>

