<%-- 
    Document   : VolunteerDetails3
    Author     : Asiah
--%>

<%@page import="com.vol.model.Volunteer"%>
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
        <title>Volunteer Details</title>
    </head>

    <body>
        <div class="an-box b">
            <div class="an-box-in c">
                <h3>My Profile</h3>

                <sql:query var="result" dataSource="${myDatasource}">
                    SELECT * FROM VOLUNTEER WHERE VOLUNTEERIC=?
                    <sql:param value="<%= session.getAttribute("volIC")%>"/>
                </sql:query>
                <c:forEach var="row" items="${result.rows}">
                    <c:set var="volIC" value="${row.volunteeric}"/>
                    <c:set var="volName" value="${row.volunteername}"/>
                    <c:set var="phoneNo" value="${row.volunteerphone}"/>
                    <c:set var="email" value="${row.volunteeremail}"/>
                </c:forEach>

                <div class="an-form">
                    <form method="">
                        <table id="update" cellpadding="4" cellspacing="4">
                            <tr>
                                <b><font color="white"><%= (request.getAttribute("errMessage") == null) ? ""
                                                            : request.getAttribute("errMessage")%></font></b>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td><input type="text" name="volName" value="${volName}" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td>IC Number</td>
                                <td><input type="text" name="volIC" value="${volIC}" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td>Telephone</td>
                                <td><input type="text" name="phoneNo" value="${phoneNo}" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" name="email" value="${email}" readonly="readonly"></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="an-btn-box">
                    <form action="VolunteerUpdate2.jsp" method="POST">
                        <input type="hidden" name="volIC" value="${volIC}" />
                        <input type="submit" value="EDIT" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

