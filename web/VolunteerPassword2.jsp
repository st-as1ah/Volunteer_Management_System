<%-- 
    Document   : VolunteerPassword2
    Author     : Asiah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sql:setDataSource var="myDatasource"
                   driver="org.apache.derby.jdbc.ClientDriver"
                   url="jdbc:derby://localhost:1527/VolunteerDB" user="APP"
                   password="abc123"/>
<jsp:include page="headerVolunteer.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Change Password</title>
    </head>

    <body>
        <div class="an-box b">
            <div class="an-box-in c">
                <h3>Change Password</h3>

                <c:set var="volIC" value="${param.volIC}"/>
                <c:set var="password" value="${param.password}"/>

                <sql:query var="result" dataSource="${myDatasource}">
                    SELECT * FROM VOLUNTEER WHERE VOLUNTEERIC=?
                    <sql:param value="${volIC}"/>
                </sql:query>
                <c:forEach var="row" items="${result.rows}">
                    <c:set var="volIC" value="${row.volunteeric}"/>
                    <c:set var="password" value="${row.volunteerpass}"/>
                </c:forEach>

                <div class="an-form">
                    <form method="POST" action="PassUpdateVolServlet">
                        <table id="update" cellpadding="4" cellspacing="4">
                            <tr>
                            <font color="white"><%= (request.getAttribute("errMessage") == null) ? ""
                                                                : request.getAttribute("errMessage")%></font>
                            </tr>
                            <tr>
                                <td>IC Number</td>
                                <td><input type="text" name="volIC" value="${volIC}" required></td>
                            </tr>
                            <tr>
                                <td>New Password</td>
                                <td><input type="password" name="password" value="" required></td>
                            </tr>
                            <tr>
                                <td>Confirm New Password</td>
                                <td><input type="password" name="confirm_pwd" value="" required></td>
                            </tr>
                        </table>
                        <div class="an-btn-box">
                            <input type="submit" value="CHANGE PASSWORD"/>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</body>
</html>

