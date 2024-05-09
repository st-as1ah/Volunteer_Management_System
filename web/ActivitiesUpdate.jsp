<%-- 
    Document   : updateActivities
    Created on : Jan 27, 2022, 4:27:02 PM
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
        <title>Update Activities</title>
    </head>
    <body>
        <jsp:include page="headerStaff.jsp" />  
        <div class="an-box b">
            <div class="an-box-in b">
                <h3>Update Activities</h3>
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
                    <c:set var="actid" value="${param.actid}"/> 
                    <c:set var="actName" value="${param.actName}"/>
                    <c:set var="actDesc" value="${param.actDesc}"/>
                    <c:set var="actLocation" value="${param.actLocation}"/>
                    <c:set var="actDate" value="${param.actDate}"/>
                    <c:set var="serviceName" value="${param.serviceName}"/>

                    <sql:query var="result" dataSource="${myDatasource}">
                        SELECT A.actid,A.actname,A.actdesc,B.actlocation,B.actdate FROM ACTIVITIES A
                        Join ACTIVITIESDESC B On A.ACTID=B.ACTID 
                        where A.ACTID=?
                        <sql:param value="${actid}"/>

                    </sql:query>
                    <c:forEach var="table" items="${result.rows}">
                        <c:set var="id_query" value="${table.actid}"/>
                        <c:set var="name_query" value="${table.actname}"/>
                        <c:set var="desc_query" value="${table.actdesc}"/>
                        <c:set var="loc_query" value="${table.actlocation}"/>
                        <c:set var="date_query" value="${table.actdate}"/>
                    </c:forEach>

                    <form method="POST" action="ActivitiesUpdateController">
                        <table id="newcert">
                            <tr>
                                <td>Activity ID</td>
                                <td><input type="text" name="actid" value="${id_query}" readonly="readonly"/></td>
                            </tr>
                            <tr>
                                <td>Activity Name</td>
                                <td><input type="text" size="60" name="actName" value="${name_query}" ></td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td><input type="text" size="80" name="actDesc" value="${desc_query}"></td>
                            </tr>	
                            <tr>
                                <td>Activity Location</td>
                                <td><input type="text" size="60" name="actLocation" value="${loc_query}"></td>
                            </tr>						
                            <tr>
                                <td><label for="act_Date">Date</td>
                                <td><input type="date"  name="actDate" min="2021-01-01" value="${date_query}" required></td>        
                            </tr>
                            <tr>
                                <td><label for="serviceName">Service Category</label></td>
                                <td>
                                    <select id="type" name="serviceName">
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
                                </td>
                                <td><div class="an-btn-box">
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