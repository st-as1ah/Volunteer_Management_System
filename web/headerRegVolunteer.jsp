<%-- 
    Document   : headerRegVolunteer
    Created on : Feb 6, 2022, 5:36:33 PM
    Author     : Asiah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    <div class="sidenav">
        <h3 class="bar-item">VMS</h3>
        <h3 class="bar-item">Welcome Volunteer </h3>
            <a href='VolunteerLogin2.jsp'>Login</a>
            <a href='VolunteerRegister2.jsp'>Register Here</a>  	
    </div>
    <div class="main">
        <div class="an-box">
            <div class="an-box-in">
                    <img src="Resources/images/kk.png" style="width:500px;height:200px" alt="pic">
                    <a>Interested In Volunteering?</a><br/><br/><br/>
                    <p class="fill">
                    <b>Simply fill in the form and click register once ready.</b></p>
            </div>
          </div>
   
    <script>
/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
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