<%-- 
    Document   : viewActivitiesFClerk
    Created on : Jan 27, 2022, 4:28:00 PM
    Author     : Fatin Amalin
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
        <title>View Activities</title>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />

        <div class="an-box">
            <div class="an-box-in b">
                <h3>View Activities</h3>
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
                    SELECT A.ACTID,A.ACTNAME,S.SERVICESNAME,A.ACTDESC,C.ACTLOCATION,C.ACTDATE
                    FROM ACTIVITIES A JOIN ACTIVITIESDESC C ON A.ACTID=C.ACTID 
                    JOIN SERVICES S ON A.SERVICESID=S.SERVICESID ORDER BY A.ACTID ASC
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
                            <td><form action="ActivitiesUpdate.jsp" method="POST">
                                    <input type="hidden" name="actid" value="${row[0]}"/> 
                                    <input type="submit" value="Update" class="h-btn u"/>
                                </form>
                                <form action="ActivitiesDeleteController" method="POST">
                                    <input type="hidden" name="actid" value="${row[0]}"/>  
                                    <input type="submit" value="Delete" class="h-btn d" onClick="deleteAct()"/>
                                </form>
                            </td>
                            <% count++;%>
                        </tr>
                    </c:forEach>
                </table>
                <div class="history-total">
                    Total : <%=count%>
                </div>
            </div>
        </div>
    </div>
    <script>
        function deleteAct() {
          var txt;
          if (confirm("Are you sure ? ")) {
                alert("Deleted succesfully !");
            } else {
                txt = "You pressed Cancel!";
            }
           else
                alert("Succesfully Deleted an activity!");
            document.getElementById("demo").innerHTML = txt;
        }
    </script>
</body>

</html>
