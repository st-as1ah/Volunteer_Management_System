<%-- 
    Document   : updateServices
    Created on : Jan 30, 2022, 8:14:02 PM
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
        <title>Update Services</title>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" /> 
        <div class="an-box b">
            <div class="an-box-in b">
                <h3>Update Services</h3>
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
                    <c:set var="servicesid" value="${param.servicesid}"/> 
                    <c:set var="servicesName" value="${param.servicesName}"/>
                    <c:set var="servicesDesc" value="${param.servicesDesc}"/>
                    <c:set var="staffname" value="${param.staffname}"/>

                    <sql:query var="result" dataSource="${myDatasource}">
                        SELECT servicesid,servicesname,servicesdesc FROM SERVICES
                        where SERVICESID=?
                        <sql:param value="${servicesid}"/>
                    </sql:query>

                    <c:forEach var="table" items="${result.rows}">
                        <c:set var="id_query" value="${table.servicesid}"/>
                        <c:set var="name_query" value="${table.servicesname}"/>
                        <c:set var="desc_query" value="${table.servicesdesc}"/>
                    </c:forEach>
                    <form method="POST" action="ServicesUpdateController">
                        <table id="newcert">
                            <tr>
                                <td>Services ID</td>
                                <td><input type="text" name="servicesid" value="${id_query}" readonly="readonly"/></td>
                            </tr>
                            <tr>
                                <td>Services Name</td>
                                <td><input type="text" size="60" name="servicesName" value="${name_query}"></td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td><input type="text" size="60"  name="servicesDesc" value="${desc_query}"></td>
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
                                </td>
                                <td><div class="an-btn-box">
                                        <input type="submit" value="Submit" name="submit"></div>
                                </td>
                            </tr>
                        </table>  
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>