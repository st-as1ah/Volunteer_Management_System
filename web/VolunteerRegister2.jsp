<%-- 
    Document   : VolunteerRegister2
    Author     : Asiah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerRegVolunteer.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Volunteer Registration</title>
    </head>
    <body>
        <div class="an-box b">
            <div class="an-box-in b">
                <h3>Fill in below</h3>
                <p>Register here in under a minute!</p>
                <div class="an-form">
                    <form method="POST" action="RegisterVolServlet">
                        <table id="newcert" >
                            <tr>
                            <font color="white"><%= (request.getAttribute("errMessage") == null) ? ""
                                                                    : request.getAttribute("errMessage")%></font>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td  colspan="2"><input type="text" name="volName" value="" class="long" required></td>
                            </tr>
                            <tr>
                                <td>IC Number</td>
                                <td><input type="text" name="volIC" value="" maxlength="12" minlength="12" required></td>
                            </tr>
                            <tr>
                                <td>Telephone</td>
                                <td><input type="text" name="phoneNo" value="" maxlength="11" minlength="10" required></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td colspan="3"><input type="text" name="email" value="" class="long" required></td>
                            </tr>							
                            <tr>
                                <td>Password: <br>
                                    <input type="password" name="password" value="" required>
                                </td>
                            </tr>
                            <tr>
                                <td>Confirm Password: <br>
                                    <input type="password" name="confirm_pwd" value="" required>
                                </td>
                            </tr>
                        </table>
                        <div class="an-btn-box">
                            <input type="submit" value="REGISTER" name="submit">
                        </div>   
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
