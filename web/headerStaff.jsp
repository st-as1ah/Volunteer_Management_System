<%-- 
    Document   : headerStaff
    Created on : Feb 5, 2022, 6:41:36 PM
    Author     : Fatin Amalin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <link href="Resources/css/main2.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
        <script>var __adobewebfontsappname__="dreamweaver"</script>
        <script src="http://use.edgefonts.net/actor:n4:default;lato:n1:default;abel:n4:default.js" type="text/javascript"></script>
    </head>
    <body>	
        <div class="sidenav"><% session.setAttribute("staffIC",session.getAttribute("staffIC")); %>
            <h3 class="bar-item">VMS</h3>
            <h3 class="bar-item">Welcome</h3>
            <button class="dropdown-btn">My Page
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <a href='StaffDetails2.jsp?staffIC="<%= session.getAttribute("staffIC") %>"'>My Details</a>
                <a href='StaffUpdate.jsp?staffIC="<%= session.getAttribute("staffIC") %>"'>Update My Details</a>
                <a href='StaffAdd.jsp'>Add Staff</a>
                <a href='StaffList.jsp'>View All Staff</a>
            </div>
            <button class="dropdown-btn">Activities
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <a href='ActivitiesAdd.jsp'>Add Activities</a>
                <a href='ActivitiesViewFStaff.jsp'>View Activities</a>
            </div>
            <button class="dropdown-btn">Services
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-container">
                <a href='ServicesAdd.jsp'>Add Services</a>
                <a href='ServicesView.jsp'>View Services</a>
            </div>

            <form method="GET" action="LogoutServlet">
                <a><input type="submit" value="Logout" name="logout"></a>
            </form>    	
        </div>
        <div class="main">
            <div class="an-box">
                <div class="an-box-in">
                    <img src="Resources/images/kk.png" style="width:500px;height:200px" alt="pic">
                    <a>Volunteer Management</a><br/><br/><br/>
                    <p class="fill">
                        <b>Simplify a hand of helping.</b></p>
                </div>
            </div>
            <script>
                /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
                var dropdown = document.getElementsByClassName("dropdown-btn");
                var i;

                for (i = 0; i < dropdown.length; i++) {
                    dropdown[i].addEventListener("click", function () {
                        this.classList.toggle("active");
                        var dropdownContent = this.nextElementSibling;
                        if (dropdownContent.style.display === "block") {
                            dropdownContent.style.display = "none";
                        } else {
                            dropdownContent.style.display = "block";
                        }
                    });
                }
            </script>
