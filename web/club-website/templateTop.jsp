<html>

<head>
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<title>CMU Ballroom Dance Club</title>
	<link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>

<%@ page import="edu.cmu.cs15437.clubwebsite.databeans.UserBean"%>

<div id="Header">
	<a href="http://www.cmubdc.org" title="CMU Ballroom">CMU Ballroom Dance Club</a>
</div>

<div id="linkbar">
  <ul>
<%  UserBean user = (UserBean) session.getAttribute("user");
	if (user == null){  %>
	<li><a href="home.jsp">Home</a>
	<li>   <a href="login.do">Login</a><br /><br />
<%	}
	else{  %>

	<li><%=user.getUserName()%><br /><br />
	<li><a href="home.jsp">Home</a>
	<li><a href="logout.do">Logout</a><br /><br />
    
    <b><font color="white"><u>RESOURCES</u></font></b>
    <li>   <a href="profile.jsp">Profile</a>
    <li>   <a href="photos.jsp">Photos</a>
    <li>   <a href="videos.jsp">Videos</a>
    <li><b><font color="white">---------------</font></b>
<%	}  %>
    
    <br /><b><font color="white"><u>CLUB</u></font></b>
    <li>   <a href="about.jsp">About</a>
    <li>   <a href="officers.jsp">Officers</a>
    <li>   <a href="calendar.jsp">Calendar</a>
    <li>   <a href="lessons.jsp">Lessons</a>
    <li>   <a href="events.jsp">Events</a>
    <li>   <a href="directions.jsp">Contact / Directions</a>
    <li>   <a href="faq.jsp">FAQ</a>
    <li>   <a href="links.jsp">Links</a> 
    <li><b><font color="white">---------------</font></b>
    
      
    
    <br /><b><font color="white"><u>COMPETING</u></font></b>
    <li>   <a href="competing.jsp">Competing</a>
    <li>   <a href="compTeam.jsp">Comp Team </a>
    <li>   <a href="syllabus_standard.jsp">Syllabus</a>
    <li>   <a href="tip_advice.jsp">Tips &amp; Advice</a>
    <li>   <a href="scotchBall.jsp">Scotch Ball </a>

  </ul>
<br />
	<a href="http://www.cmubdc.org" 
     title="CMU Ballroom"><img src="http://www.cmubdc.org/images/logo.jpg" width="110" /></a>
</div>

