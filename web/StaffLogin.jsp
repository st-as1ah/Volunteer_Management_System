<%@page import="util.DBConnection"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Staff Login</title>
<link href="Resources/css/main.css" rel="stylesheet" type="text/css"/>
<!--<link href="css/main.css" rel="stylesheet" type="text/css">-->
		<style type="text/css">
		body{margin:0;};
	</style>
<!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
<script>var __adobewebfontsappname__="dreamweaver"</script>
<script src="http://use.edgefonts.net/lato:n1:default;actor:n4:default;abel:n4:default.js" type="text/javascript"></script>

</head>

<body>
    
    <div class="limiter">
        <div class="container">
            <div class="login">
                <div class="login-info">
                    <div class="login-header a">Volunteer Management System</div>
                        <div class="login-img">
                             <img src="Resources/images/Dayflow - Park Life.png" style="width:100%;height:100%" alt="student">
                            <a class="login-w">SIMPLIFY A HAND OF HELPING</a>
                            <br><a class="login-w1"></a>
                        </div>
                </div>
                <div class="login-input">
                    <div class="login-header"><a>WELCOME !</a><a><br>Sign in to your account STAFF</a>
                        <br><br> Or Are you a 				
                            <a href="VolunteerLogin2.jsp"><font color="white">VOLUNTEER</font></a> ?<br>
                           <font color="red"><%= (request.getAttribute("errMessage")== null)? ""
                                            : request.getAttribute("errMessage")%></font>
                    </div>
                    <div class="login-details">
                        <form method="POST" action="LoginStaffServlet">
                            <%
                                String credential = (String) session.getAttribute("credential");
                                if (credential != null) {
                                    session.removeAttribute("credential");
                            %>
                            <div class="alert alert-danger" id="danger"><span style="color:red">You have enter wrong credentials.</span></div>
                            <%
                                }
                            %>
                            <div class="log">
                                <div class="usern">Username</div>
                                <div class="login-in"><input type="text" class="ab" name="staffic" placeholder="Identity Card" required></div>
                            </div>
                            <div class="log">
                                <div class="usern">Password</div>
                                <div class="login-in"><input type="password" class="ab" name="staffpass" required></div>
                            
								<!--<br><div class="fp">Forgot Password ?</div>-->
                            </div>
                            <div class="log" style="text-align: center">
                                <input type="submit" class="submit-login" value="Login" name="login">
                            </div>
					
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
